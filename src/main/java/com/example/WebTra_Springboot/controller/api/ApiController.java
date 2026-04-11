package com.example.WebTra_Springboot.controller.api;

import com.example.WebTra_Springboot.model.*;
import com.example.WebTra_Springboot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private BrewingGuideService brewingGuideService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatBotService chatbotService;

    // ===== Products =====
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(@RequestParam(required = false) Long categoryId) {
        if (categoryId != null) {
            return ResponseEntity.ok(productService.getProductsByCategory(categoryId));
        }
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(product);
    }

    @GetMapping("/products/{id}/reviews")
    public ResponseEntity<List<Review>> getProductReviews(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReviewsByProductId(id));
    }

    @PostMapping("/products/{id}/reviews")
    public ResponseEntity<Review> submitReview(@PathVariable Long id, @RequestBody Review review) {
        Product product = productService.getProductById(id);
        if (product == null) return ResponseEntity.notFound().build();
        review.setProduct(product);
        return ResponseEntity.ok(reviewService.saveReview(review));
    }

    // ===== Categories =====
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    // ===== Blogs =====
    @GetMapping("/blogs")
    public ResponseEntity<List<Blog>> getBlogs(@RequestParam(required = false) Long categoryId) {
        if (categoryId != null) {
            return ResponseEntity.ok(blogService.getBlogsByCategory(categoryId));
        }
        return ResponseEntity.ok(blogService.getAllBlogs());
    }

    @GetMapping("/blogs/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable Long id) {
        Blog blog = blogService.getBlogById(id);
        if (blog == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(blog);
    }

    @GetMapping("/blog-categories")
    public ResponseEntity<List<BlogCategory>> getBlogCategories() {
        return ResponseEntity.ok(blogService.getAllCategories());
    }

    // ===== Orders (authenticated user) =====
    @PostMapping("/orders")
    public ResponseEntity<Map<String, String>> createOrder(@RequestBody Order order) {
        return ResponseEntity.ok(Map.of("message", "Đặt hàng thành công"));
    }

    @GetMapping("/orders/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(order);
    }

    // ===== User Profile =====
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateProfile(@PathVariable Long id, @RequestBody User user) {
        User updated = userService.updateProfile(id, user);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/users/{id}/change-password")
    public ResponseEntity<Map<String, String>> changePassword(@PathVariable Long id, @RequestBody Map<String, String> passwords) {
        boolean success = userService.changePassword(id, passwords.get("oldPassword"), passwords.get("newPassword"));
        if (success) {
            return ResponseEntity.ok(Map.of("message", "Đổi mật khẩu thành công"));
        }
        return ResponseEntity.status(400).body(Map.of("message", "Mật khẩu cũ không chính xác"));
    }

    // ===== Chatbot =====
    @GetMapping("/chatbot")
    public ResponseEntity<Map<String, Object>> chatbotSearch(
            @RequestParam(name = "query", defaultValue = "") String query,
            @RequestParam(name = "userId", required = false) Long userId,
            @RequestParam(name = "lastProductId", required = false) Long lastProductId) {
            
        String queryLower = query.toLowerCase().trim();
        Map<String, Object> response = new HashMap<>();
        
        Map<String, Object> advancedResponse = chatbotService.findAdvancedResponse(queryLower);
        
        // Handle specific order lookup (by ID)
        if (advancedResponse != null && advancedResponse.containsKey("orderId")) {
            Long orderId = (Long) advancedResponse.get("orderId");
            Order order = orderService.getOrderById(orderId);
            if (order != null) {
                String statusMsg = "📦 Đơn hàng #" + orderId + " của bạn đang ở trạng thái: **" + translateStatus(order.getStatus()) + "**. ";
                statusMsg += "Tổng tiền: " + String.format("%,.0f", order.getTotalAmount()) + "đ. ";
                statusMsg += "Dự kiến giao đến: " + order.getShippingAddress();
                response.put("message", statusMsg);
                response.put("suggestions", List.of("Kiểm tra đơn khác", "Liên hệ hỗ trợ"));
                response.put("reasoning", "Tôi đã truy xuất dữ liệu từ hệ thống đơn hàng của WebTra.");
                return ResponseEntity.ok(response);
            }
        }

        // Handle generic message responses from chatbot service
        if (advancedResponse != null && advancedResponse.containsKey("message")) {
            // Check if it's a generic "Find delicious tea" request, in which case we SHOULD also show some products
            if (queryLower.contains("ngon") || queryLower.contains("tìm trà") || queryLower.contains("gợi ý")) {
                // Let it fall through to search database for "ngon" products
            } else {
                response.putAll(advancedResponse);
                return ResponseEntity.ok(response);
            }
        }

        // 1.6 Handle "Tea Types / Categories" intent
        if (queryLower.contains("loại trà") || queryLower.contains("danh mục") || 
            queryLower.contains("có những trà nào") || queryLower.contains("list trà") || 
            queryLower.contains("danh sách trà") || queryLower.contains("tất cả trà")) {
            List<Category> categories = categoryService.getAllCategories();
            StringBuilder msg = new StringBuilder("🌿 WebTra có rất nhiều dòng trà thượng hạng phục vụ bạn:\n\n");
            List<String> suggestions = new java.util.ArrayList<>();
            for (Category c : categories) {
                msg.append("- ").append(c.getName()).append("\n");
                suggestions.add(c.getName());
            }
            msg.append("\nBạn nhấn vào tên loại trà dưới đây để xem chi tiết nhé!");
            
            response.put("message", msg.toString());
            response.put("suggestions", suggestions);
            response.put("reasoning", "Tôi đã liệt kê các danh mục trà hiện có tại cửa hàng.");
            return ResponseEntity.ok(response);
        }

        // 1.5 Handle "Check Recent Orders" intent
        if (queryLower.contains("kiểm tra đơn hàng") || queryLower.contains("đơn hàng của tôi")) {
            if (userId != null) {
                List<Order> userOrders = orderService.getOrdersByUserId(userId);
                if (userOrders != null && !userOrders.isEmpty()) {
                    // Sort by ID descending (assume newer is larger)
                    userOrders.sort((o1, o2) -> o2.getId().compareTo(o1.getId()));
                    
                    StringBuilder msg = new StringBuilder("📦 Bạn có **" + userOrders.size() + "** đơn hàng. Đây là trạng thái các đơn gần nhất:\n\n");
                    for (int i = 0; i < Math.min(3, userOrders.size()); i++) {
                        Order o = userOrders.get(i);
                        msg.append("- **#").append(o.getId()).append("**: ").append(translateStatus(o.getStatus()))
                           .append(" (").append(String.format("%,.0f", o.getTotalAmount())).append("đ)\n");
                    }
                    if (userOrders.size() > 3) {
                        msg.append("\n... và một số đơn hàng khác.");
                    }
                    
                    response.put("message", msg.toString());
                    response.put("suggestions", List.of("Tìm trà ngon", "Liên hệ hỗ trợ"));
                    response.put("reasoning", "Tôi đã tra cứu danh sách đơn hàng đã mua của bạn.");
                    return ResponseEntity.ok(response);
                } else {
                    response.put("message", "Dạ, tài khoản của bạn hiện chưa có đơn hàng nào trên hệ thống WebTra ạ. Bạn hãy thử tìm món trà ưng ý và đặt hàng nhé!");
                    response.put("suggestions", List.of("Tìm trà ngon", "Khuyến mãi"));
                    return ResponseEntity.ok(response);
                }
            } else {
                response.put("message", "Để kiểm tra đơn hàng, bạn vui lòng **Đăng nhập** tài khoản hoặc nhập mã đơn hàng trực tiếp (Ví dụ: **#123**) để tôi tra cứu giúp bạn nhé!");
                response.put("suggestions", List.of("Đăng nhập", "Tìm trà ngon"));
                return ResponseEntity.ok(response);
            }
        }

        if (advancedResponse != null && advancedResponse.containsKey("message")) {
            response.putAll(advancedResponse);
            return ResponseEntity.ok(response);
        }

        // 2. Clean Search Query for Database Lookup
        String cleanedQuery = queryLower
            .replaceAll("^(tìm|mua|xem|cho|tôi|cần|muốn|là|gì|đâu|nào|hỏi về|thông tin về|biết về|bán|có|trà|ai|kiểm tra|check|dưới|khoảng|tầm|giá|rẻ)\\s+", "")
            .replaceAll("\\s+(là gì|ở đâu|nào|đấy|nhỉ|hả|vậy|không|thế|với|ạ|đi|nhỉ|dưới|khoảng|tầm|giá|rẻ)$", "")
            .trim();
        
        // Remove numbers and units for pure keyword search
        String keywordOnly = cleanedQuery.replaceAll("(\\d+[.\\d]*)\\s*(k|tr|triệu|đ|vnđ)?", "").trim();
        if (keywordOnly.isEmpty()) keywordOnly = cleanedQuery;

        List<Product> products = new java.util.ArrayList<>();

        // Special handling for the "Tìm trà khác" button
        if (queryLower.contains("tìm trà khác") || queryLower.equals("sản phẩm mới")) {
            products = productService.getAllProducts();
        } else {
            // 3. Perform Keyword Search
            products = new java.util.ArrayList<>(productService.searchProducts(keywordOnly));
        }
        
        // Handle Price Filter
        if (advancedResponse != null && advancedResponse.containsKey("maxPrice")) {
            Double maxPrice = (Double) advancedResponse.get("maxPrice");
            
            // If keyword search results are empty for the specific price request, show all products filtered by price
            if (products.isEmpty() || keywordOnly.length() < 2) {
                products = new java.util.ArrayList<>(productService.getAllProducts());
            }
            
            products = products.stream()
                .filter(p -> p.getPrice() != null && p.getPrice() <= maxPrice)
                .sorted((p1, p2) -> p2.getPrice().compareTo(p1.getPrice())) // Sort descending: closest to maxPrice first
                .collect(java.util.stream.Collectors.toList());
        }

        List<Blog> blogs = blogService.searchBlogs(cleanedQuery);
        List<BrewingGuide> guides = brewingGuideService.searchGuides(cleanedQuery);
        
        // Final fallback to original query if cleaned search failed
        if (products.isEmpty() && blogs.isEmpty() && guides.isEmpty() && !cleanedQuery.equals(queryLower)) {
            products = productService.searchProducts(queryLower);
            blogs = blogService.searchBlogs(queryLower);
            guides = brewingGuideService.searchGuides(queryLower);
        }

        // Add Social Proof to products (Optimized)
        List<Map<String, Object>> productResults = products.stream().limit(10).map(p -> {
            Map<String, Object> pMap = new HashMap<>();
            pMap.put("id", p.getId());
            pMap.put("name", p.getName());
            pMap.put("price", p.getPrice());
            pMap.put("imageUrl", p.getImageUrl());
            pMap.put("origin", p.getOrigin());
            pMap.put("weights", p.getWeights()); // Added weights for cart functionality
            if (p.getCategory() != null) {
                pMap.put("categoryName", p.getCategory().getName());
            }
            
            List<Review> reviews = reviewService.getReviewsByProductId(p.getId());
            if (reviews != null && !reviews.isEmpty()) {
                pMap.put("reviewCount", reviews.size());
                pMap.put("avgRating", reviews.stream()
                    .filter(r -> r.getRating() != null)
                    .mapToInt(Review::getRating)
                    .average()
                    .orElse(0.0));
            } else {
                pMap.put("reviewCount", 0);
                pMap.put("avgRating", 0.0);
            }
            return pMap;
        }).collect(java.util.stream.Collectors.toList());

        response.put("products", productResults);
        response.put("blogs", blogs);
        response.put("guides", guides);
        response.put("hasMore", products.size() > 10);
        response.put("total", products.size());
        
        if (productResults.isEmpty() && blogs.isEmpty() && guides.isEmpty()) {
            response.put("message", "Dạ, WebTra chưa tìm thấy nội dung cho '" + query + "'. Bạn thử tìm theo tên trà hoặc mã đơn hàng (vd: #1) nhé! 🌿");
            response.put("suggestions", List.of("Trà Thái Nguyên", "Trà Ô long", "Kiểm tra đơn hàng"));
        } else {
            String foundMsg = "Dạ, tôi đã tìm thấy thông tin bạn cần về '" + (cleanedQuery.length() > 2 ? cleanedQuery : query) + "':";
            if (advancedResponse != null && advancedResponse.containsKey("maxPrice")) {
                foundMsg = "Dạ, đây là các loại trà giá tốt (dưới " + String.format("%,.0f", (Double)advancedResponse.get("maxPrice")) + "đ) cho bạn:";
            }
            response.put("message", foundMsg);
            
            List<String> dynamicSuggestions = new java.util.ArrayList<>();
            if (!productResults.isEmpty()) {
                dynamicSuggestions.add("Đánh giá của " + productResults.get(0).get("name"));
                dynamicSuggestions.add("Cách pha " + productResults.get(0).get("name"));
            }
            dynamicSuggestions.add("Trà dưới 200k");
            dynamicSuggestions.add("Liên hệ hỗ trợ");
            response.put("suggestions", dynamicSuggestions);
            
            // Personalized Reasoning
            StringBuilder reasoning = new StringBuilder("Tôi đã tra cứu cơ sở dữ liệu và tìm thấy các kết quả khớp.");
            if (lastProductId != null) {
                Product lastP = productService.getProductById(lastProductId);
                if (lastP != null) {
                    reasoning.append(" Bên cạnh trà ").append(lastP.getName()).append(" bạn đã xem, thì đây cũng là những lựa chọn tuyệt vời tương tự!");
                }
            }
            if (!productResults.isEmpty() && (int)productResults.get(0).get("reviewCount") > 0) {
                reasoning.append(" Đặc biệt, sản phẩm '").append(productResults.get(0).get("name")).append("' đang được đánh giá rất tốt đó ạ!");
            }
            response.put("reasoning", reasoning.toString());
        }
        
        return ResponseEntity.ok(response);
    }

    private String translateStatus(String status) {
        if (status == null) return "Đang xử lý";
        switch (status) {
            case "Pending": return "Chờ xác nhận";
            case "Processing": return "Đang chuẩn bị hàng";
            case "Completed": return "Đã giao thành công";
            case "Cancelled": return "Đã hủy";
            default: return status;
        }
    }
}
