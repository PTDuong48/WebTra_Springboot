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

/**
 * Controller xử lý các yêu cầu liên quan đến thanh toán.
 * Bao gồm: tạo giao dịch mới và tiếp nhận kết quả trả về từ cổng VNPAY.
 */
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

    /**
     * API Tạo URL thanh toán VNPAY dựa trên thông tin đơn hàng.
     * 1. Lưu đơn hàng vào Database với trạng thái "Pending".
     * 2. Xây dựng tham số gửi sang VNPAY.
     */
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
        
        // Tạo chuỗi mã giao dịch ngẫu nhiên gồm 8 chữ số
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
        
        // Gán mã tham chiếu giao dịch cho đơn hàng
        order.setVnpTxnRef(vnp_TxnRef);

        // Duyệt qua danh sách sản phẩm trong giỏ hàng để tạo chi tiết đơn hàng (OrderDetails)
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
        // Lưu toàn bộ đơn hàng và chi tiết vào CSDL
        orderService.createOrder(order);

        // 2. Tạo VNPAY Payment URL
        long vnp_Amount = totalAmount.longValue() * 100; // Số tiền tính theo đơn vị 'VND*100' (cents)
        String vnp_TmnCode = VnPayConfig.vnp_TmnCode;

        // Tập hợp các tham số cần thiết để gửi cho VNPAY
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
        // Tạo mã băm SecureHash để đảm bảo dữ liệu không bị thay đổi dọc đường
        String vnp_SecureHash = VnPayConfig.hmacSHA512(VnPayConfig.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        // URL hoàn chỉnh để chuyển hướng người dùng sang cổng thanh toán VNPAY
        String paymentUrl = VnPayConfig.vnp_PayUrl + "?" + queryUrl;

        return ResponseEntity.ok(Map.of("url", paymentUrl));
    }

    /**
     * API nhận kết quả trả về từ VNPAY (Server-side callback).
     * Dùng để cập nhật trạng thái đơn hàng ngầm nếu frontend không kịp phản hồi.
     */
    @GetMapping("/vnpay-ipn")
    public ResponseEntity<?> vnpayIPN(@RequestParam Map<String, String> allParams) {
        return processPaymentCallback(allParams);
    }

    /**
     * API nhận người dùng quay trở lại từ VNPAY (Client-side redirect).
     */
    @GetMapping("/vnpay-return")
    public ResponseEntity<?> vnpayReturn(@RequestParam Map<String, String> allParams) {
        return processPaymentCallback(allParams);
    }

    /**
     * Hàm dùng chung để xử lý xác thực chữ ký và cập nhật trạng thái đơn hàng.
     */
    private ResponseEntity<?> processPaymentCallback(Map<String, String> allParams) {
        String vnp_SecureHash = allParams.get("vnp_SecureHash");
        System.out.println("VNPAY Callback Params: " + allParams);
        
        Map<String, String> fields = new HashMap<>();
        for (Map.Entry<String, String> entry : allParams.entrySet()) {
            if (entry.getKey() != null && entry.getKey().startsWith("vnp_")) {
                fields.put(entry.getKey(), entry.getValue());
            }
        }
        fields.remove("vnp_SecureHashType");
        fields.remove("vnp_SecureHash");
        
        List<String> fieldNames = new ArrayList<>(fields.keySet());
        Collections.sort(fieldNames); // Danh sách tham số phải được sắp xếp theo bảng chữ cái để tạo hash đúng
        
        // Cách 1: Không Encode (VNPAY 2.1.0 chuẩn mới)
        StringBuilder resultNoEncode = new StringBuilder();
        // Cách 2: Có Encode (Standard encoding '+' for space)
        StringBuilder resultEncoded = new StringBuilder();
        
        boolean first = true;
        for (String fieldName : fieldNames) {
            String fieldValue = fields.get(fieldName);
            if (fieldValue != null && fieldValue.length() > 0) {
                if (!first) {
                    resultNoEncode.append("&");
                    resultEncoded.append("&");
                }
                resultNoEncode.append(fieldName).append("=").append(fieldValue);
                try {
                    resultEncoded.append(fieldName).append("=").append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                } catch (UnsupportedEncodingException e) {
                    resultEncoded.append(fieldName).append("=").append(fieldValue);
                }
                first = false;
            }
        }
        
        String signNoEncode = VnPayConfig.hmacSHA512(VnPayConfig.vnp_HashSecret, resultNoEncode.toString());
        String signEncoded = VnPayConfig.hmacSHA512(VnPayConfig.vnp_HashSecret, resultEncoded.toString());
        
        System.out.println("VNPAY Hash Data (NoEncode): [" + resultNoEncode + "]");
        System.out.println("VNPAY Hash Data (Encoded): [" + resultEncoded + "]");
        System.out.println("VNPAY Received Hash: [" + vnp_SecureHash + "]");
        System.out.println("VNPAY Calculated (NoEncode): [" + signNoEncode + "]");
        System.out.println("VNPAY Calculated (Encoded): [" + signEncoded + "]");
        
        // Kiểm tra mã băm nhận được có khớp với mã băm hệ thống tính toán không (Hợp lệ chữ ký)
        if (signNoEncode.equalsIgnoreCase(vnp_SecureHash) || signEncoded.equalsIgnoreCase(vnp_SecureHash)) {
            String vnp_TxnRef = allParams.get("vnp_TxnRef");
            String responseCode = allParams.get("vnp_ResponseCode");
            System.out.println("VNPAY Hash matched! (using dual check)");

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
