package com.ga.todo.controller;

import com.ga.todo.model.Category;
import com.ga.todo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class CategoryController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello World!";
    }

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category categoryObject){
        System.out.println("calling create category");
        return categoryService.createCategory(categoryObject);
    }

    @GetMapping("/categories")
    public List<Category> getCategories(){
        System.out.println("Calling getCategories");
        return categoryService.getCategories();
    }

    @GetMapping("/category/{id}")
    public Optional<Category> getCategory(@PathVariable long id){
        System.out.println("Calling getCategories");
        return categoryService.getCategory(id);
    }


    @PutMapping("/categories/{id}")
    public Category updateCategory(@PathVariable long id, @RequestBody Category updatedCategory) {
        System.out.println("Calling updateCategory");
        return categoryService.updateCategory(id, updatedCategory);
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable long id) {
        System.out.println("Calling deleteCategory");
        categoryService.deleteCategory(id);
    }

}
