package com.example.WebTra_Springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/story")
    public String story() {
        return "story";
    }

    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }

    @GetMapping("/products")
    public String products() {
        return "products";
    }

    @GetMapping("/product-detail")
    public String productDetail() {
        return "product-detail";
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }

    @GetMapping("/checkout")
    public String checkout() {
        return "checkout";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @GetMapping("/profile/orders")
    public String profileOrders() {
        return "profile-orders";
    }

    @GetMapping("/profile/password")
    public String profilePassword() {
        return "profile-password";
    }

    @GetMapping("/profile/address")
    public String profileAddress() {
        return "profile-address";
    }
}
