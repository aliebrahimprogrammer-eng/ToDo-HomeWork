package com.ga.todo.repository;

import com.ga.todo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByUserIdAndName(Long userId, String categoryName);

    List<Category> findByUserId(Long userId);

    Category findByIdAndUserId(Long categoryId, Long userId);
}
