package com.example.WebTra_Springboot.service;

import com.example.WebTra_Springboot.model.Blog;
import com.example.WebTra_Springboot.model.BlogCategory;
import java.util.List;

public interface BlogService {
    List<Blog> getAllBlogs();
    List<Blog> getBlogsByCategory(Long categoryId);
    List<Blog> searchBlogs(String query);
    Blog getBlogById(Long id);

    Blog saveBlog(Blog blog);
    void deleteBlog(Long id);
    
    List<BlogCategory> getAllCategories();
    BlogCategory getCategoryById(Long id);
    BlogCategory saveCategory(BlogCategory category);
    void deleteCategory(Long id);
}
