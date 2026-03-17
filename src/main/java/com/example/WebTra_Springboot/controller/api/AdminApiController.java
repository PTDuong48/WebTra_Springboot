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
@RequestMapping("/api/admin")
public class AdminApiController {

    @Autowired private ProductService productService;
    @Autowired private CategoryService categoryService;
    @Autowired private OrderService orderService;
    @Autowired private UserService userService;
    @Autowired private BlogService blogService;
    @Autowired private ReviewService reviewService;
    @Autowired private BrewingGuideService brewingGuideService;

    // ===== Dashboard =====
    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> dashboard() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalOrders", orderService.getTotalOrders());
        stats.put("totalRevenue", orderService.getTotalRevenue());
        List<Product> products = productService.getAllProducts();
        stats.put("totalProducts", products != null ? (long) products.size() : 0L);
        stats.put("totalUsers", userService.getTotalUsers());
        stats.put("latestOrders", orderService.getLatestOrders(5));
        stats.put("categoryPerformance", orderService.getCategoryPerformance());
        return ResponseEntity.ok(stats);
    }

    // ===== Products =====
    @GetMapping("/products")
    public ResponseEntity<Map<String, Object>> products() {
        Map<String, Object> data = new HashMap<>();
        data.put("products", productService.getAllProducts());
        data.put("categories", categoryService.getAllCategories());
        return ResponseEntity.ok(data);
    }

    @PostMapping("/products")
    public ResponseEntity<?> saveProduct(@RequestBody Product product) {
        try {
            return ResponseEntity.ok(productService.saveProduct(product));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage(), "details", e.getCause() != null ? e.getCause().getMessage() : "No details"));
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(Map.of("message", "Đã xóa sản phẩm"));
    }

    // ===== Categories =====
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> categories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.saveCategory(category));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Map<String, String>> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(Map.of("message", "Đã xóa danh mục"));
    }

    // ===== Orders =====
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> orders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PutMapping("/orders/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(orderService.updateOrderStatus(id, body.get("status")));
    }

    // ===== Customers =====
    @GetMapping("/customers")
    public ResponseEntity<List<User>> customers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<User> getCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/customers")
    public ResponseEntity<User> saveCustomer(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Map<String, String>> deleteCustomer(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(Map.of("message", "Đã xóa khách hàng"));
    }

    // ===== Blogs =====
    @GetMapping("/blogs")
    public ResponseEntity<List<Blog>> blogs() {
        return ResponseEntity.ok(blogService.getAllBlogs());
    }

    @PostMapping("/blogs")
    public ResponseEntity<Blog> saveBlog(@RequestBody Blog blog) {
        return ResponseEntity.ok(blogService.saveBlog(blog));
    }

    @DeleteMapping("/blogs/{id}")
    public ResponseEntity<Map<String, String>> deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return ResponseEntity.ok(Map.of("message", "Đã xóa bài viết"));
    }

    // ===== Blog Categories =====
    @GetMapping("/blog-categories")
    public ResponseEntity<List<BlogCategory>> blogCategories() {
        return ResponseEntity.ok(blogService.getAllCategories());
    }

    @PostMapping("/blog-categories")
    public ResponseEntity<BlogCategory> saveBlogCategory(@RequestBody BlogCategory category) {
        return ResponseEntity.ok(blogService.saveCategory(category));
    }

    @DeleteMapping("/blog-categories/{id}")
    public ResponseEntity<Map<String, String>> deleteBlogCategory(@PathVariable Long id) {
        blogService.deleteCategory(id);
        return ResponseEntity.ok(Map.of("message", "Đã xóa danh mục blog"));
    }

    // ===== Reviews =====
    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> reviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Map<String, String>> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok(Map.of("message", "Đã xóa đánh giá"));
    }

    // ===== Brewing Guides =====
    @GetMapping("/brewing-guides")
    public ResponseEntity<List<BrewingGuide>> brewingGuides() {
        return ResponseEntity.ok(brewingGuideService.getAllGuides());
    }

    @PostMapping("/brewing-guides")
    public ResponseEntity<BrewingGuide> saveBrewingGuide(@RequestBody BrewingGuide guide) {
        return ResponseEntity.ok(brewingGuideService.saveGuide(guide));
    }

    @DeleteMapping("/brewing-guides/{id}")
    public ResponseEntity<Map<String, String>> deleteBrewingGuide(@PathVariable Long id) {
        brewingGuideService.deleteGuide(id);
        return ResponseEntity.ok(Map.of("message", "Đã xóa hướng dẫn pha trà"));
    }
}
