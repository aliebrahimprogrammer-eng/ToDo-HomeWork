package com.ga.todo.service;

import com.ga.todo.model.Category;
import com.ga.todo.model.Item;
import com.ga.todo.exception.InformationNotFoundException;
import com.ga.todo.repository.CategoryRepository;
import com.ga.todo.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemService {

    private CategoryRepository categoryRepository;
    private ItemRepository itemRepository;

    public Item createItem(Long categoryId, Item item){
        System.out.println("Service calling createItem ==>");
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new InformationNotFoundException(
                                "Category with id " + categoryId + " not found"
                        ));
        item.setCategory(category);
        return itemRepository.save(item);
    }

    public List<Item> getItems() {
        System.out.println("Service calling getItems ==>");
        return itemRepository.findAll();
    }

    public Optional<Item> getItem(Long categoryId, Long itemId) {

        Optional<Item> item = itemRepository.findById(itemId);

        if (item.isPresent()
                && item.get().getCategory().getId().equals(categoryId)) {
            return item;
        }

        return Optional.empty();
    }

    public List<Item> getItemsByCategory(Long categoryId) {
        System.out.println("Service calling getItemsByCategory ==>");
        return itemRepository.findByCategoryId(categoryId);
    }

    public Item updateItem(
            Long categoryId,
            Long itemId,
            Item updatedItem
    ) {

        Optional<Item> optionalItem = itemRepository.findById(itemId);

        if (optionalItem.isPresent()) {

            Item item = optionalItem.get();

            if (!item.getCategory().getId().equals(categoryId)) {
                throw new InformationNotFoundException(
                        "Item does not belong to category " + categoryId
                );
            }

            item.setName(updatedItem.getName());
            item.setDescription(updatedItem.getDescription());
            item.setDueDate(updatedItem.getDueDate());

            return itemRepository.save(item);
        }

        throw new InformationNotFoundException(
                "Item with id " + itemId + " not found"
        );
    }

    public void deleteItem(Long categoryId, Long itemId) {

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() ->
                        new InformationNotFoundException(
                                "Item with id " + itemId + " not found"
                        )
                );

        if (!item.getCategory().getId().equals(categoryId)) {
            throw new InformationNotFoundException(
                    "Item does not belong to category " + categoryId
            );
        }

        itemRepository.delete(item);
    }

}
