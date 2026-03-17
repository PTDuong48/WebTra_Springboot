package com.example.WebTra_Springboot.service;

import com.example.WebTra_Springboot.model.BlogCategory;
import java.util.List;

public interface BlogCategoryService {
    List<BlogCategory> getAllBlogCategories();
    BlogCategory getBlogCategoryById(Long id);
    BlogCategory saveBlogCategory(BlogCategory category);
    void deleteBlogCategory(Long id);
}
