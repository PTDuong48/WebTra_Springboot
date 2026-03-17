package com.example.WebTra_Springboot.service;

import com.example.WebTra_Springboot.model.Product;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(Long categoryId);
    Product getProductById(Long id);
    Product saveProduct(Product product);
    void deleteProduct(Long id);
}
