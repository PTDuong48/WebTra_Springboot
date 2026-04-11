package com.example.WebTra_Springboot.service.impl;

import com.example.WebTra_Springboot.model.Blog;
import com.example.WebTra_Springboot.model.BlogCategory;
import com.example.WebTra_Springboot.repository.BlogCategoryRepository;
import com.example.WebTra_Springboot.repository.BlogRepository;
import com.example.WebTra_Springboot.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private BlogCategoryRepository blogCategoryRepository;

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public List<Blog> getBlogsByCategory(Long categoryId) {
        return blogRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Blog> searchBlogs(String query) {
        if (query == null || query.trim().isEmpty()) {
            return new java.util.ArrayList<>();
        }
        
        String[] keywords = query.toLowerCase().split("\\s+");
        List<Blog> allBlogs = blogRepository.findAll();
        
        return allBlogs.stream()
            .filter(blog -> {
                String title = (blog.getTitle() != null ? blog.getTitle().toLowerCase() : "");
                String content = (blog.getContent() != null ? blog.getContent().toLowerCase() : "");
                String combined = title + " " + content;
                
                for (String word : keywords) {
                    if (!combined.contains(word)) return false;
                }
                return true;
            })
            .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }

    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public List<BlogCategory> getAllCategories() {
        return blogCategoryRepository.findAll();
    }

    @Override
    public BlogCategory getCategoryById(Long id) {
        return blogCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public BlogCategory saveCategory(BlogCategory category) {
        return blogCategoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        blogCategoryRepository.deleteById(id);
    }
}
