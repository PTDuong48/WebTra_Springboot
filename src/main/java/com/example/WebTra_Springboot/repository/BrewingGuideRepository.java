package com.example.WebTra_Springboot.repository;

import com.example.WebTra_Springboot.model.BrewingGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrewingGuideRepository extends JpaRepository<BrewingGuide, Long> {
    java.util.List<BrewingGuide> findByTitleContainingIgnoreCase(String title);
}


