package com.example.WebTra_Springboot.service.impl;

import com.example.WebTra_Springboot.model.BrewingGuide;
import com.example.WebTra_Springboot.repository.BrewingGuideRepository;
import com.example.WebTra_Springboot.service.BrewingGuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BrewingGuideServiceImpl implements BrewingGuideService {

    @Autowired
    private BrewingGuideRepository brewingGuideRepository;

    @Override
    public List<BrewingGuide> getAllGuides() {
        return brewingGuideRepository.findAll();
    }

    @Override
    public BrewingGuide getGuideById(Long id) {
        return brewingGuideRepository.findById(id).orElse(null);
    }

    @Override
    public BrewingGuide saveGuide(BrewingGuide guide) {
        return brewingGuideRepository.save(guide);
    }

    @Override
    public void deleteGuide(Long id) {
        brewingGuideRepository.deleteById(id);
    }
}
