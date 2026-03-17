package com.example.WebTra_Springboot.service.impl;

import com.example.WebTra_Springboot.model.Product;
import com.example.WebTra_Springboot.repository.ProductRepository;
import com.example.WebTra_Springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Product saveProduct(Product product) {
        // Ensure relationships are set correctly if needed
        if (product.getWeights() != null && !product.getWeights().isEmpty()) {
            product.getWeights().forEach(w -> w.setProduct(product));
            // Sync top-level price and stock for database compatibility
            product.setPrice(product.getWeights().get(0).getPrice());
            product.setStock(product.getWeights().get(0).getStock());
        }
        if (product.getImages() != null) {
            product.getImages().forEach(i -> i.setProduct(product));
        }
        if (product.getBrewingGuides() != null) {
            product.getBrewingGuides().forEach(g -> g.setProduct(product));
        }
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
