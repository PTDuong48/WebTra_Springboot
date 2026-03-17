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
