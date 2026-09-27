package com.ga.todo.controller;

import com.ga.todo.model.Category;
import com.ga.todo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // TEST
    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    // CREATE CATEGORY
    @PostMapping("/categories")
    public Category createCategory(
            @RequestBody Category categoryObject
    ) {

        System.out.println("Controller: calling create category");

        return categoryService.createCategory(categoryObject);
    }

    // GET ALL CATEGORIES
    @GetMapping("/categories")
    public List<Category> getCategories() {

        System.out.println("Controller: calling getCategories");

        return categoryService.getCategories();
    }

    // GET ONE CATEGORY
    @GetMapping("/categories/{categoryId}")
    public Category getCategory(
            @PathVariable Long categoryId
    ) {

        System.out.println("Controller: calling getCategory");

        return categoryService.getCategory(categoryId);
    }

    // UPDATE CATEGORY
    @PutMapping("/categories/{categoryId}")
    public Category updateCategory(
            @PathVariable Long categoryId,
            @RequestBody Category updatedCategory
    ) {

        System.out.println("Controller: calling updateCategory");

        return categoryService.updateCategory(
                categoryId,
                updatedCategory
        );
    }

    // DELETE CATEGORY
    @DeleteMapping("/categories/{categoryId}")
    public void deleteCategory(
            @PathVariable Long categoryId
    ) {

        System.out.println("Controller: calling deleteCategory");

        categoryService.deleteCategory(categoryId);
    }
}