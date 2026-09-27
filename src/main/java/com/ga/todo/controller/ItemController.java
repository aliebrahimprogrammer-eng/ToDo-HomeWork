package com.ga.todo.controller;

import com.ga.todo.model.Item;
import com.ga.todo.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;

    // CREATE ITEM
    @PostMapping("/categories/{categoryId}/items")
    public Item createItem(
            @PathVariable Long categoryId,
            @RequestBody Item itemObject
    ) {

        System.out.println(
                "Controller: calling createItem"
        );

        return itemService.createItem(
                categoryId,
                itemObject
        );
    }

    // GET ITEM BY ID
    @GetMapping("/categories/{categoryId}/items/{itemId}")
    public Item getItem(
            @PathVariable Long categoryId,
            @PathVariable Long itemId
    ) {

        System.out.println(
                "Controller: calling getItem"
        );

        return itemService.getItem(
                categoryId,
                itemId
        );
    }

    // GET ALL ITEMS IN CATEGORY
    @GetMapping("/categories/{categoryId}/items")
    public List<Item> getItemsByCategory(
            @PathVariable Long categoryId
    ) {

        System.out.println(
                "Controller: calling getItemsByCategory"
        );

        return itemService.getItemsByCategory(
                categoryId
        );
    }

    // UPDATE ITEM
    @PutMapping("/categories/{categoryId}/items/{itemId}")
    public Item updateItem(
            @PathVariable Long categoryId,
            @PathVariable Long itemId,
            @RequestBody Item updatedItem
    ) {

        System.out.println(
                "Controller: calling updateItem"
        );

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

        System.out.println(
                "Controller: calling deleteItem"
        );

        itemService.deleteItem(
                categoryId,
                itemId
        );
    }
}