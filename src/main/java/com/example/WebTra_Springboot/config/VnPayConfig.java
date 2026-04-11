package com.example.WebTra_Springboot.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Configuration
public class VnPayConfig {
    // URL cổng thanh toán VNPAY
    public static String vnp_PayUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    // URL nhận kết quả trả về từ VNPAY sau khi thanh toán
    public static String vnp_ReturnUrl = "http://localhost:5173/payment-result"; 
    // Mã định danh merchant (Terminal ID) được cấp bởi VNPAY
    public static String vnp_TmnCode = "U79RZC2G"; 
    // Chuỗi bí mật dùng để tạo mã băm (Checksum) để đảm bảo tính toàn vẹn dữ liệu
    public static String vnp_HashSecret = "XSZA3MOYHRZYT1J210AGDQQB3TJ1PVUL"; 
    // URL API truy vấn trạng thái giao dịch
    public static String vnp_apiUrl = "https://sandbox.vnpayment.vn/merchantv2/api/transaction";

    /**
     * Hàm tạo mã băm bảo mật HMAC-SHA512.
     * Dùng để xác thực dữ liệu gửi/nhận từ VNPAY.
     */
    public static String hmacSHA512(final String key, final String data) {
        try {
            if (key == null || data == null) {
                throw new NullPointerException();
            }
            final Mac hmac512 = Mac.getInstance("HmacSHA512");
            byte[] hmacKeyBytes = key.getBytes();
            final SecretKeySpec secretKey = new SecretKeySpec(hmacKeyBytes, "HmacSHA512");
            hmac512.init(secretKey);
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] result = hmac512.doFinal(dataBytes);
            StringBuilder sb = new StringBuilder(2 * result.length);
            for (byte b : result) {
                sb.append(String.format("%02X", b & 0xff));
            }
            return sb.toString();
        } catch (Exception ex) {
            return "";
        }
    }

    /**
     * Lấy địa chỉ IP của người dùng.
     * Hỗ trợ lấy qua Header X-FORWARDED-FOR khi có Proxy/Load Balancer.
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ipAdress;
        try {
            ipAdress = request.getHeader("X-FORWARDED-FOR");
            if (ipAdress == null) {
                ipAdress = request.getRemoteAddr();
            }
        } catch (Exception e) {
            ipAdress = "Invalid IP:" + e.getMessage();
        }
        return ipAdress;
    }

    /**
     * Tạo mã số ngẫu nhiên cho tham số giao dịch.
     */
    public static String getRandomNumber(int len) {
        Random rnd = new Random();
        String chars = "0123456789";
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
