package com.example.WebTra_Springboot.service;

import com.example.WebTra_Springboot.model.BrewingGuide;
import java.util.List;

public interface BrewingGuideService {
    List<BrewingGuide> getAllGuides();
    BrewingGuide getGuideById(Long id);
    BrewingGuide saveGuide(BrewingGuide guide);
    void deleteGuide(Long id);
}
