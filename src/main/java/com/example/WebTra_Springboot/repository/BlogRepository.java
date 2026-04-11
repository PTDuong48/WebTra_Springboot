package com.example.WebTra_Springboot.repository;

import com.example.WebTra_Springboot.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    java.util.List<Blog> findByCategoryId(Long categoryId);
    java.util.List<Blog> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title, String content);

}
