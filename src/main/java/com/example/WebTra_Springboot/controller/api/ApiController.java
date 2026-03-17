package com.example.WebTra_Springboot.controller.api;

import com.example.WebTra_Springboot.model.*;
import com.example.WebTra_Springboot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private OrderService orderService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

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
        // In real app, associate with current user
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
        // Simplified - in real app, get user from security context
        return ResponseEntity.ok(Map.of("message", "Đặt hàng thành công"));
    }

    @GetMapping("/orders/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
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
}
