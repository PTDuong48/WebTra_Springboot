package com.example.WebTra_Springboot.controller.api;

import com.example.WebTra_Springboot.config.VnPayConfig;
import com.example.WebTra_Springboot.model.*;
import com.example.WebTra_Springboot.repository.*;
import com.example.WebTra_Springboot.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/create-payment")
    @SuppressWarnings("unchecked")
    public ResponseEntity<?> createPayment(HttpServletRequest req, @RequestBody Map<String, Object> requestData) throws UnsupportedEncodingException {
        Map<String, Object> orderData = (Map<String, Object>) requestData.get("orderData");
        Long userId = ((Number) orderData.get("userId")).longValue();
        Double totalAmount = ((Number) orderData.get("amount")).doubleValue();
        List<Map<String, Object>> items = (List<Map<String, Object>>) orderData.get("items");

        // Trích xuất thông tin giao hàng từ orderData hoặc requestData
        // Lưu ý: Checkout.jsx gửi { amount, orderData: { userId, amount, items } }
        // Nhưng form giao hàng nằm trong state formData ở Checkout.jsx và không thấy được gửi trực tiếp trong handleCheckout?
        // Đợi đã, handleCheckout trong Checkout.jsx hiện tại chỉ gửi { userId, amount, items }. 
        // Tôi cần sửa cả Checkout.jsx để gửi kèm formData.
        
        String vnp_TxnRef = VnPayConfig.getRandomNumber(8);
        
        // 1. Tạo và lưu Order vào Database
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "User không tồn tại"));
        }

        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(totalAmount);
        order.setStatus("Pending");
        
        // Lấy thông tin từ request nếu có, nếu không lấy từ user mặc định
        Map<String, Object> shippingInfo = (Map<String, Object>) orderData.get("shippingInfo");
        if (shippingInfo != null) {
            order.setShippingName((String) shippingInfo.get("fullName"));
            order.setShippingPhone((String) shippingInfo.get("phone"));
            order.setShippingEmail((String) shippingInfo.get("email"));
            order.setShippingAddress((String) shippingInfo.get("address"));
            order.setShippingFee(((Number) shippingInfo.get("shippingFee")).doubleValue());
            order.setPaymentMethod((String) shippingInfo.get("paymentMethod"));
        } else {
            order.setShippingName(user.getFullName());
            order.setShippingPhone(user.getPhone());
            order.setShippingEmail(user.getEmail());
            order.setShippingAddress(user.getAddress());
            order.setShippingFee(30000.0);
            order.setPaymentMethod("vnpay");
        }
        
        order.setVnpTxnRef(vnp_TxnRef);

        List<OrderDetail> details = new ArrayList<>();
        for (Map<String, Object> item : items) {
            Long productId = ((Number) item.get("productId")).longValue();
            Double price = ((Number) item.get("price")).doubleValue();
            Integer quantity = ((Number) item.get("quantity")).intValue();

            Product product = productRepository.findById(productId).orElse(null);
            if (product != null) {
                OrderDetail detail = new OrderDetail();
                detail.setOrder(order);
                detail.setProduct(product);
                detail.setPrice(price);
                detail.setQuantity(quantity);
                details.add(detail);
            }
        }
        order.setDetails(details);
        orderService.createOrder(order);

        // 2. Tạo VNPAY Payment URL
        long vnp_Amount = totalAmount.longValue() * 100;
        String vnp_TmnCode = VnPayConfig.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", "2.1.0");
        vnp_Params.put("vnp_Command", "pay");
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(vnp_Amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", "NCB");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", VnPayConfig.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", VnPayConfig.getIpAddress(req));
        vnp_Params.put("vnp_OrderType", "other");

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator<String> itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VnPayConfig.hmacSHA512(VnPayConfig.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VnPayConfig.vnp_PayUrl + "?" + queryUrl;

        return ResponseEntity.ok(Map.of("url", paymentUrl));
    }

    @GetMapping("/vnpay-ipn")
    public ResponseEntity<?> vnpayIPN(@RequestParam Map<String, String> allParams) {
        return processPaymentCallback(allParams);
    }

    @GetMapping("/vnpay-return")
    public ResponseEntity<?> vnpayReturn(@RequestParam Map<String, String> allParams) {
        return processPaymentCallback(allParams);
    }

    private ResponseEntity<?> processPaymentCallback(Map<String, String> allParams) {
        String vnp_SecureHash = allParams.get("vnp_SecureHash");
        Map<String, String> fields = new HashMap<>(allParams);
        fields.remove("vnp_SecureHashType");
        fields.remove("vnp_SecureHash");

        // Verify Hash
        List<String> fieldNames = new ArrayList<>(fields.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        Iterator<String> itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) fields.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                hashData.append(fieldName);
                hashData.append('=');
                try {
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if (itr.hasNext()) {
                    hashData.append('&');
                }
            }
        }

        String signValue = VnPayConfig.hmacSHA512(VnPayConfig.vnp_HashSecret, hashData.toString());
        if (signValue.equals(vnp_SecureHash)) {
            String vnp_TxnRef = allParams.get("vnp_TxnRef");
            String responseCode = allParams.get("vnp_ResponseCode");

            Optional<Order> orderOpt = orderRepository.findByVnpTxnRef(vnp_TxnRef);
            if (orderOpt.isPresent()) {
                Order order = orderOpt.get();
                if ("00".equals(responseCode)) {
                    order.setStatus("Processing");
                    orderRepository.save(order);
                    return ResponseEntity.ok(Map.of("status", "success", "message", "Thanh toán thành công"));
                } else {
                    order.setStatus("Cancelled");
                    orderRepository.save(order);
                    return ResponseEntity.ok(Map.of("status", "failed", "message", "Thanh toán thất bại"));
                }
            }
            return ResponseEntity.ok(Map.of("status", "error", "message", "Đơn hàng không tồn tại"));
        }
        return ResponseEntity.ok(Map.of("status", "error", "message", "Chữ ký không hợp lệ"));
    }
}
