package com.ga.todo.controller;

import com.ga.todo.model.Item;
import com.ga.todo.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class ItemController {

    private ItemService itemService;

    //CREATE ITEM
    @PostMapping("categories/{categoryId}/items")
    public Item createItem(
            @PathVariable(value = "categoryId") Long categoryId,
            @RequestBody Item itemObject
    ){
        System.out.println("calling createItem from controller");
        return itemService.createItem(categoryId,itemObject);
    }

    // GET ITEM BY ID
    @GetMapping("/categories/{categoryId}/items/{itemId}")
    public Optional<Item> getItem(
            @PathVariable Long categoryId,
            @PathVariable Long itemId
    ) {
        System.out.println("Calling getItem from controller");
        return itemService.getItem(categoryId, itemId);
    }

    // GET ITEMS BY CATEGORY
    @GetMapping("/categories/{categoryId}/items")
    public List<Item> getItemsByCategory(
            @PathVariable Long categoryId
    ) {
        System.out.println("Calling getItemsByCategory from controller");
        return itemService.getItemsByCategory(categoryId);
    }

    // UPDATE ITEM
    @PutMapping("/categories/{categoryId}/items/{itemId}")
    public Item updateItem(
            @PathVariable Long categoryId,
            @PathVariable Long itemId,
            @RequestBody Item updatedItem
    ) {
        System.out.println("Calling updateItem from controller");

        return itemService.updateItem(
                categoryId,
                itemId,
                updatedItem
        );
    }

    // DELETE ITEM
    @DeleteMapping("/categories/{categoryId}/items/{itemId}")
    public void deleteItem(
            @PathVariable Long categoryId,
            @PathVariable Long itemId
    ) {
        System.out.println("Calling deleteItem from controller");

        itemService.deleteItem(categoryId, itemId);
    }

}
