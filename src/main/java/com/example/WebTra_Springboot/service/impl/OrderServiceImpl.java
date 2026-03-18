package com.example.WebTra_Springboot.service.impl;

import com.example.WebTra_Springboot.model.Order;
import com.example.WebTra_Springboot.model.OrderDetail;
import com.example.WebTra_Springboot.repository.OrderRepository;
import com.example.WebTra_Springboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll(Sort.by(Sort.Direction.DESC, "orderDate"));
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order updateOrderStatus(Long id, String status) {
        Order order = getOrderById(id);
        if (order != null) {
            order.setStatus(status);
            return orderRepository.save(order);
        }
        return null;
    }

    @Override
    public List<Order> getLatestOrders(int limit) {
        return orderRepository.findAll(PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "orderDate"))).getContent();
    }

    @Override
    public Long getTotalOrders() {
        return orderRepository.count();
    }

    @Override
    public Double getTotalRevenue() {
        return orderRepository.findAll().stream()
                .filter(o -> "Completed".equals(o.getStatus()))
                .mapToDouble(o -> o.getTotalAmount() != null ? o.getTotalAmount() : 0.0)
                .sum();
    }

    @Override
    public Order createOrder(Order order) {
        if (order.getDetails() != null) {
            for (OrderDetail detail : order.getDetails()) {
                detail.setOrder(order);
            }
        }
        order.setStatus("Pending");
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public Map<String, Double> getCategoryPerformance() {
        List<Order> completedOrders = orderRepository.findAll().stream()
                .filter(o -> "Completed".equals(o.getStatus()))
                .toList();

        Map<String, Double> categoryRevenue = new HashMap<>();
        double totalRev = 0;

        for (Order order : completedOrders) {
            if (order.getDetails() != null) {
                for (OrderDetail detail : order.getDetails()) {
                    if (detail.getProduct() != null && detail.getProduct().getCategory() != null) {
                        String catName = detail.getProduct().getCategory().getName();
                        double rev = (detail.getPrice() != null ? detail.getPrice() : 0.0) * (detail.getQuantity() != null ? detail.getQuantity() : 0);
                        categoryRevenue.put(catName, categoryRevenue.getOrDefault(catName, 0.0) + rev);
                        totalRev += rev;
                    }
                }
            }
        }

        Map<String, Double> performance = new HashMap<>();
        if (totalRev > 0) {
            for (Map.Entry<String, Double> entry : categoryRevenue.entrySet()) {
                double percentage = (entry.getValue() / totalRev) * 100;
                performance.put(entry.getKey(), Math.round(percentage * 10.0) / 10.0); // Làm tròn 1 chữ số thập phân
            }
        }
        return performance;
    }
}
