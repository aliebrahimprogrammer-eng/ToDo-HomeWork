package com.ga.todo.service;

import com.ga.todo.model.Category;
import com.ga.todo.exception.InformationExistException;
import com.ga.todo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(Category categoryObject){
        System.out.println("Service: calling create category");
        Category category = categoryRepository.findByName(categoryObject.getName());
        if(category!=null){
            throw new InformationExistException("Category with name " + category.getName() + " already exists");
        }else{
            return categoryRepository.save(categoryObject);
        }
    }

    public List<Category> getCategories() {
        System.out.println("Service calling getCategories");
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategory(long id) {
        System.out.println("Service calling getCategory");
        return categoryRepository.findById(id);
    }


    public Category updateCategory(long id, Category updatedCategory) {
        System.out.println("Service calling updateCategory");

        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();

            category.setName(updatedCategory.getName());
            category.setDescription(updatedCategory.getDescription());

            return categoryRepository.save(category);
        }

        return null;
    }

    public void deleteCategory(long id) {
        System.out.println("Service calling deleteCategory");

        categoryRepository.deleteById(id);
    }
}