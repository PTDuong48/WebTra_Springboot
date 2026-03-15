package com.example.WebTra_Springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "admin-dashboard";
    }

    @GetMapping("/products")
    public String products() {
        return "admin-products";
    }

    @GetMapping("/categories")
    public String categories() {
        return "admin-categories";
    }

    @GetMapping("/orders")
    public String orders() {
        return "admin-orders";
    }

    @GetMapping("/customers")
    public String customers() {
        return "admin-customers";
    }

    @GetMapping("/reports")
    public String reports() {
        return "admin-reports";
    }

    @GetMapping("/settings")
    public String settings() {
        return "admin-settings";
    }

    @GetMapping("/blogs")
    public String blogs() {
        return "admin-blogs";
    }

    @GetMapping("/blog-categories")
    public String blogCategories() {
        return "admin-blog-categories";
    }
}
