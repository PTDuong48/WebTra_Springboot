package com.example.WebTra_Springboot.repository;

import com.example.WebTra_Springboot.model.ProductWeight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductWeightRepository extends JpaRepository<ProductWeight, Long> {
}
