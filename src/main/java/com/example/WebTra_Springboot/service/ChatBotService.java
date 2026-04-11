package com.example.WebTra_Springboot.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Dịch vụ ChatBot xử lý các yêu cầu từ người dùng.
 * Sử dụng kết hợp khớp từ khóa, biểu thức chính quy (Regex) và thuật toán khoảng cách Levenshtein 
 * để hiểu ý định (intent) của người dùng.
 */
@Service
public class ChatBotService {

    /**
     * Bản đồ lưu trữ các phản hồi cố định dựa trên từ khóa (Intent).
     */
    private static final Map<String, String> INTENT_RESPONSES = new HashMap<>();

    static {
        // Lời chào
        String welcome = "Chào bạn! 👋 WebTra rất vui được hỗ trợ bạn. Bạn muốn tìm trà thơm, xem bài viết hay cần hướng dẫn pha trà?";
        INTENT_RESPONSES.put("chào", welcome);
        INTENT_RESPONSES.put("hi", welcome);
        INTENT_RESPONSES.put("hello", welcome);
        INTENT_RESPONSES.put("alo", welcome);
        INTENT_RESPONSES.put("hey", welcome);

        // Liên hệ & Thông tin
        INTENT_RESPONSES.put("địa chỉ", "📍 WebTra tọa lạc tại: 123 Đường Trà, Quận 1, TP. Hồ Chí Minh. Rất mong được đón tiếp bạn!");
        INTENT_RESPONSES.put("liên hệ", "📞 Bạn có thể gọi Hotline: 0123.456.789 hoặc nhắn tin qua Fanpage WebTra nhé.");
        INTENT_RESPONSES.put("sđt", "📞 Hotline của chúng tôi là: 0123.456.789.");
        INTENT_RESPONSES.put("hotline", "📞 Hotline hỗ trợ 24/7: 0123.456.789.");
        INTENT_RESPONSES.put("facebook", "🌐 Fanpage chính thức: facebook.com/webtra.vn. Bạn inbox để được tư vấn kĩ hơn nhé!");

        // Giao hàng & Thanh toán
        INTENT_RESPONSES.put("ship", "🚚 WebTra giao hàng toàn quốc! Miễn phí ship cho đơn hàng trên 500k. Nội thành HCM nhận hàng trong 24h.");
        INTENT_RESPONSES.put("giao hàng", "🚚 Thời gian giao hàng dự kiến: nội thành 1 ngày, các tỉnh khác 2-4 ngày làm việc.");
        INTENT_RESPONSES.put("thanh toán", "💳 Chúng tôi hỗ trợ thanh toán COD (khi nhận hàng), chuyển khoản ngân hàng hoặc ví điện tử MoMo/ZaloPay.");

        // Lịch sự & Xã giao
        INTENT_RESPONSES.put("cảm ơn", "Dạ không có gì ạ! 🥰 Chúc bạn một ngày tràn đầy năng lượng và thưởng trà thật ngon.");
        INTENT_RESPONSES.put("thanks", "You're welcome! Cần gì bạn cứ nhắn tôi nhé.");
        INTENT_RESPONSES.put("tạm biệt", "Tạm biệt bạn! Chúc bạn có những phút giây thư giãn tuyệt vời bên tách trà.");
        INTENT_RESPONSES.put("ok", "Dạ vâng ạ! 👌");

        // Các câu hỏi cụ thể
        INTENT_RESPONSES.put("giá", "Mỗi loại trà có mức giá khác nhau tùy trọng lượng. Bạn hãy nhập tên trà (vd: Ô long) để tôi báo giá chi tiết nhé!");
        INTENT_RESPONSES.put("khuyến mãi", "🎁 WebTra đang có ưu đãi giảm 10% cho đơn hàng đầu tiên khi đăng ký thành viên đó ạ!");
        INTENT_RESPONSES.put("ngon", "Trà WebTra đều được tuyển chọn kỹ lưỡng từ các vùng trà nổi tiếng. Bạn thử dùng Trà Xanh Thái Nguyên hoặc Ô Long xem sao nhé!");
        INTENT_RESPONSES.put("bán gì", "Dạ, WebTra chuyên cung cấp các loại trà đặc sản như Trà Xanh, Ô Long, Trà Sen, Trà Nhài và phụ kiện pha trà cao cấp.");
        INTENT_RESPONSES.put("uống trà có tác dụng gì", "Trà xanh rất tốt cho sức khỏe: giúp tỉnh táo, chống oxy hóa, hỗ trợ giảm cân và bảo vệ tim mạch đó bạn!");
        INTENT_RESPONSES.put("mở cửa", "⏰ Cửa hàng mở cửa từ 8:00 đến 21:00 tất cả các ngày trong tuần (kể cả Lễ, Tết). Rất mong được đón tiếp bạn!");
    }

    /**
     * Phương thức xử lý nâng cao để tìm phản hồi phù hợp nhất cho câu truy vấn.
     * 
     * @param query Câu hỏi từ người dùng
     * @return Map chứa thông tin phản hồi, gợi ý và các dữ liệu trích xuất (ID đơn hàng, giá...)
     */
    public Map<String, Object> findAdvancedResponse(String query) {
        String lowerQuery = query.toLowerCase().trim();
        Map<String, Object> result = new HashMap<>();
        
        // 1. Nhận diện theo dõi đơn hàng (Sử dụng Regex)
        java.util.regex.Pattern orderPattern = java.util.regex.Pattern.compile("(đơn hàng|mã đơn|#)\\s*(\\d+)", java.util.regex.Pattern.CASE_INSENSITIVE);
        java.util.regex.Matcher orderMatcher = orderPattern.matcher(lowerQuery);
        if (orderMatcher.find()) {
            result.put("orderId", Long.parseLong(orderMatcher.group(2)));
            result.put("reasoning", "Tôi sẽ kiểm tra trạng thái đơn hàng #" + orderMatcher.group(2) + " cho bạn.");
            return result;
        }

        // 2. Trích xuất ngưỡng giá (Ví dụ: "trà dưới 200k")
        Double maxPrice = extractPriceThreshold(lowerQuery);
        if (maxPrice != null) {
            result.put("maxPrice", maxPrice);
            result.put("reasoning", "Tôi sẽ tìm các sản phẩm có giá dưới " + String.format("%,.0f", maxPrice) + "đ cho bạn.");
        }

        // 3. Khớp từ khóa chính xác hoặc chứa từ khóa (Độ ưu tiên cao nhất)
        for (Map.Entry<String, String> entry : INTENT_RESPONSES.entrySet()) {
            String key = entry.getKey();
            if (lowerQuery.equals(key) || lowerQuery.startsWith(key + " ") || lowerQuery.endsWith(" " + key)) {
                result.put("message", entry.getValue());
                result.put("suggestions", getSuggestionsForIntent(key));
                return result;
            }
        }

        // 4. Khớp mờ (Fuzzy Matching - Xử lý lỗi chính tả) (Độ ưu tiên trung bình)
        String bestMatch = null;
        int minDistance = 3; // Ngưỡng tối đa cho lỗi chính tả (số ký tự khác biệt)
        
        for (String key : INTENT_RESPONSES.keySet()) {
            int distance = calculateLevenshteinDistance(lowerQuery, key);
            if (distance < minDistance) {
                minDistance = distance;
                bestMatch = key;
            }
        }
        
        if (bestMatch != null && result.get("maxPrice") == null && result.get("orderId") == null) {
            result.put("message", INTENT_RESPONSES.get(bestMatch));
            result.put("suggestions", getSuggestionsForIntent(bestMatch));
            result.put("reasoning", "Tôi đoán bạn đang hỏi về '" + bestMatch + "'.");
            return result;
        }

        return result.isEmpty() ? null : result;
    }

    /**
     * Trích xuất con số về giá từ câu truy vấn của người dùng.
     * Hỗ trợ các định dạng như: "200k", "1 triệu", "500.000đ", "dưới 100k".
     * 
     * @param query Câu truy vấn
     * @return Giá trị số thực (Double) hoặc null nếu không tìm thấy
     */
    private Double extractPriceThreshold(String query) {
        // Biểu thức chính quy: hỗ trợ tiền tố (dưới, khoảng...), số có dấu chấm và đơn vị (k, tr, triệu)
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("(dưới|tầm|khoảng|giá|rẻ)?\\s*(\\d+[.\\d]*)\\s*(k|tr|triệu|đ|vnđ)?", java.util.regex.Pattern.CASE_INSENSITIVE);
        java.util.regex.Matcher matcher = pattern.matcher(query);
        
        while (matcher.find()) {
            String numStr = matcher.group(2).replace(".", "");
            String unit = matcher.group(3);
            String prefix = matcher.group(1);
            
            // Nếu không có tiền tố liên quan đến giá hoặc đơn vị, bỏ qua để tránh nhầm với mã sản phẩm
            if (prefix == null && unit == null) continue;

            try {
                double value = Double.parseDouble(numStr);
                if (unit != null) {
                    unit = unit.toLowerCase();
                    if (unit.equals("k")) value *= 1000;
                    else if (unit.equals("tr") || unit.equals("triệu")) value *= 1000000;
                } else if (value < 1000) {
                    value *= 1000; // Tự động hiểu là 'k' cho các số nhỏ nếu có tiền tố giá
                }
                return value;
            } catch (NumberFormatException e) {
                // Tiếp tục tìm kiếm nếu kết quả hiện tại không phải là số hợp lệ
            }
        }
        return null;
    }

    /**
     * Lấy các gợi ý dựa trên ý định của người dùng.
     */
    private List<String> getSuggestionsForIntent(String intent) {
        switch (intent) {
            case "chào": case "hi": case "hello":
                return List.of("Tìm trà ngon", "Trà dưới 500k", "Khuyến mãi");
            case "giá":
                return List.of("Trà Thái Nguyên", "Trà Ô long", "Dưới 200k");
            case "ship": case "giao hàng":
                return List.of("Thanh toán", "Địa chỉ", "Phí ship");
            default:
                return List.of("Sản phẩm mới", "Hướng dẫn pha trà", "Liên hệ");
        }
    }

    /**
     * Thuật toán tính khoảng cách Levenshtein giữa hai chuỗi.
     * Dùng để xác định mức độ giống nhau, phục vụ việc sửa lỗi chính tả.
     * 
     * @return Số thao tác tối thiểu (chèn, xóa, sửa) để biến s1 thành s2
     */
    private int calculateLevenshteinDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= s2.length(); j++) dp[0][j] = j;

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + cost);
            }
        }
        return dp[s1.length()][s2.length()];
    }

    // Phương thức cũ dùng để tương thích ngược nếu cần
    public String findResponse(String query) {
        Map<String, Object> response = findAdvancedResponse(query);
        return response != null ? (String) response.get("message") : null;
    }
}
