package com.ga.todo.service;

import com.ga.todo.exception.InformationExistException;
import com.ga.todo.exception.InformationNotFoundException;
import com.ga.todo.model.Category;
import com.ga.todo.model.User;
import com.ga.todo.repository.CategoryRepository;
import com.ga.todo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Get the currently logged-in user from the JWT authentication
    public User getCurrentLoggedInUser() {

        MyUserDetails userDetails =
                (MyUserDetails) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();

        return userDetails.getUser();
    }

    // CREATE CATEGORY
    public Category createCategory(Category categoryObject) {

        System.out.println("Service: calling create category");

        User currentUser = getCurrentLoggedInUser();

        // Check if this user already has a category with this name
        Category existingCategory =
                categoryRepository.findByUserIdAndName(
                        currentUser.getId(),
                        categoryObject.getName()
                );

        if (existingCategory != null) {

            throw new InformationExistException(
                    "Category with name "
                            + existingCategory.getName()
                            + " already exists"
            );
        }

        // Associate category with logged-in user
        categoryObject.setUser(currentUser);

        return categoryRepository.save(categoryObject);
    }

    // GET ALL CATEGORIES FOR CURRENT USER
    public List<Category> getCategories() {

        System.out.println("Service: calling getCategories");

        User currentUser = getCurrentLoggedInUser();

        return categoryRepository.findByUserId(
                currentUser.getId()
        );
    }

    // GET ONE CATEGORY FOR CURRENT USER
    public Category getCategory(Long categoryId) {

        System.out.println("Service: calling getCategory");

        User currentUser = getCurrentLoggedInUser();

        Category category =
                categoryRepository.findByIdAndUserId(
                        categoryId,
                        currentUser.getId()
                );

        if (category == null) {

            throw new InformationNotFoundException(
                    "Category with id "
                            + categoryId
                            + " not found"
            );
        }

        return category;
    }

    // UPDATE CATEGORY
    public Category updateCategory(
            Long categoryId,
            Category updatedCategory
    ) {

        System.out.println("Service: calling updateCategory");

        User currentUser = getCurrentLoggedInUser();

        Category category =
                categoryRepository.findByIdAndUserId(
                        categoryId,
                        currentUser.getId()
                );

        if (category == null) {

            throw new InformationNotFoundException(
                    "Category with id "
                            + categoryId
                            + " not found"
            );
        }

        category.setName(updatedCategory.getName());
        category.setDescription(updatedCategory.getDescription());

        return categoryRepository.save(category);
    }

    // DELETE CATEGORY
    public void deleteCategory(Long categoryId) {

        System.out.println("Service: calling deleteCategory");

        User currentUser = getCurrentLoggedInUser();

        Category category =
                categoryRepository.findByIdAndUserId(
                        categoryId,
                        currentUser.getId()
                );

        if (category == null) {

            throw new InformationNotFoundException(
                    "Category with id "
                            + categoryId
                            + " not found"
            );
        }

        categoryRepository.delete(category);
    }
}