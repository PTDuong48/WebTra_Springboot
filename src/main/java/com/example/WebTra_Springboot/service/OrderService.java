package com.example.WebTra_Springboot.service;

import com.example.WebTra_Springboot.model.Order;
import java.util.List;
import java.util.Map;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    Order updateOrderStatus(Long id, String status);
    List<Order> getLatestOrders(int limit);
    Long getTotalOrders();
    Double getTotalRevenue();
    Order createOrder(Order order);
    List<Order> getOrdersByUserId(Long userId);
    Map<String, Double> getCategoryPerformance();
}
