package com.example.WebTra_Springboot.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "brewing_guides")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrewingGuide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties({"brewingGuides", "weights", "images", "reviews"})
    private Product product;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;
}
