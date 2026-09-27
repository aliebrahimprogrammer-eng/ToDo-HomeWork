package com.ga.todo.service;

import com.ga.todo.exception.InformationNotFoundException;
import com.ga.todo.model.Category;
import com.ga.todo.model.Item;
import com.ga.todo.model.User;
import com.ga.todo.repository.CategoryRepository;
import com.ga.todo.repository.ItemRepository;
import com.ga.todo.security.MyUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemService {

    private CategoryRepository categoryRepository;
    private ItemRepository itemRepository;

    // Get currently logged-in user from JWT
    private User getCurrentLoggedInUser() {

        MyUserDetails userDetails =
                (MyUserDetails) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();

        return userDetails.getUser();
    }

    // CREATE ITEM
    public Item createItem(
            Long categoryId,
            Item item
    ) {

        System.out.println("Service: calling createItem");

        User currentUser = getCurrentLoggedInUser();

        // Make sure category belongs to current user
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

        // Set category
        item.setCategory(category);

        // Set owner
        item.setUser(currentUser);

        return itemRepository.save(item);
    }

    // GET ALL ITEMS FOR A CATEGORY
    public List<Item> getItemsByCategory(
            Long categoryId
    ) {

        System.out.println(
                "Service: calling getItemsByCategory"
        );

        User currentUser = getCurrentLoggedInUser();

        // Make sure category belongs to current user
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

        return itemRepository.findByCategoryIdAndUserId(
                categoryId,
                currentUser.getId()
        );
    }

    // GET ONE ITEM
    public Item getItem(
            Long categoryId,
            Long itemId
    ) {

        System.out.println("Service: calling getItem");

        User currentUser = getCurrentLoggedInUser();

        Item item =
                itemRepository.findByIdAndUserId(
                        itemId,
                        currentUser.getId()
                );

        if (item == null) {

            throw new InformationNotFoundException(
                    "Item with id "
                            + itemId
                            + " not found"
            );
        }

        // Make sure item belongs to requested category
        if (!item.getCategory().getId().equals(categoryId)) {

            throw new InformationNotFoundException(
                    "Item with id "
                            + itemId
                            + " does not belong to category "
                            + categoryId
            );
        }

        return item;
    }

    // UPDATE ITEM
    public Item updateItem(
            Long categoryId,
            Long itemId,
            Item updatedItem
    ) {

        System.out.println("Service: calling updateItem");

        User currentUser = getCurrentLoggedInUser();

        Item item =
                itemRepository.findByIdAndUserId(
                        itemId,
                        currentUser.getId()
                );

        if (item == null) {

            throw new InformationNotFoundException(
                    "Item with id "
                            + itemId
                            + " not found"
            );
        }

        // Make sure item belongs to category
        if (!item.getCategory().getId().equals(categoryId)) {

            throw new InformationNotFoundException(
                    "Item with id "
                            + itemId
                            + " does not belong to category "
                            + categoryId
            );
        }

        item.setName(updatedItem.getName());
        item.setDescription(updatedItem.getDescription());
        item.setDueDate(updatedItem.getDueDate());

        return itemRepository.save(item);
    }

    // DELETE ITEM
    public void deleteItem(
            Long categoryId,
            Long itemId
    ) {

        System.out.println("Service: calling deleteItem");

        User currentUser = getCurrentLoggedInUser();

        Item item =
                itemRepository.findByIdAndUserId(
                        itemId,
                        currentUser.getId()
                );

        if (item == null) {

            throw new InformationNotFoundException(
                    "Item with id "
                            + itemId
                            + " not found"
            );
        }

        // Make sure item belongs to category
        if (!item.getCategory().getId().equals(categoryId)) {

            throw new InformationNotFoundException(
                    "Item with id "
                            + itemId
                            + " does not belong to category "
                            + categoryId
            );
        }

        itemRepository.delete(item);
    }
}