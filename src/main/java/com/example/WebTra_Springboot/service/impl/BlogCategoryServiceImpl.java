package com.example.WebTra_Springboot.service.impl;

import com.example.WebTra_Springboot.model.BlogCategory;
import com.example.WebTra_Springboot.repository.BlogCategoryRepository;
import com.example.WebTra_Springboot.service.BlogCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogCategoryServiceImpl implements BlogCategoryService {

    @Autowired
    private BlogCategoryRepository blogCategoryRepository;

    @Override
    public List<BlogCategory> getAllBlogCategories() {
        return blogCategoryRepository.findAll();
    }

    @Override
    public BlogCategory getBlogCategoryById(Long id) {
        return blogCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public BlogCategory saveBlogCategory(BlogCategory category) {
        return blogCategoryRepository.save(category);
    }

    @Override
    public void deleteBlogCategory(Long id) {
        blogCategoryRepository.deleteById(id);
    }
}
