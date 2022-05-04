package com.bolton.demospringboot0903.controller;

import com.bolton.demospringboot0903.Models.Category;
import com.bolton.demospringboot0903.Models.Item;
import com.bolton.demospringboot0903.Service.CartService;
import com.bolton.demospringboot0903.Service.CategoryService;
import com.bolton.demospringboot0903.Service.ItemService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;

@RestController
@RequestMapping("/Item")
public class ItemController {
    private final ItemService itemService;
    private final CategoryService categoryService;

    public ItemController(ItemService itemService, CategoryService categoryService) {
        this.itemService = itemService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems()
    {
        List<Item> items = itemService.findAll();
        return new ResponseEntity<>(items, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable("id") Long id)
    {
        Item item = itemService.findItemById(id);
        return new ResponseEntity<>(item, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/getItemsByName/{name}")
    public ResponseEntity<List<Item>> getItemsContainingName(@PathVariable("name") String name)
    {
        List<Item> items = itemService.findAllContainingName(name);
        return new ResponseEntity<>(items, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/getItemsByCategoryName/{name}")
    public ResponseEntity<List<Item>> getItemsContainingCategoryName(@PathVariable("name") String name)
    {
        List<Item> items = itemService.findAllContainingCategoryName(name);
        return new ResponseEntity<>(items, HttpStatus.OK); //ok is 200 status code
    }

//suggestions completely random
    @GetMapping("/getRandomSuggestions/{amount}")
    public ResponseEntity<List<Item>> getRandomSuggestions(@PathVariable("amount") int amount)
    {
        List<Item> items = itemService.findRandom(amount);
        return new ResponseEntity<>(items, HttpStatus.OK); //ok is 200 status code
    }

    //gets random items for home page
    @GetMapping("/getRandomSuggestionsLists")
    public ResponseEntity<Item[][]> FindRandomListsForHome()
    {
        Item[][] items = itemService.FindRandomForHome();
        return new ResponseEntity<>(items, HttpStatus.OK); //ok is 200 status code
    }

    @PostMapping("/add")
    public ResponseEntity<Item> addItem(@RequestBody Item item)
    {
        if (item.getCategory().getId() == null){
            Category defaultCategory = categoryService.findDefault("undefined");
            if ( defaultCategory == null){
                Category newCategory = categoryService.addCategory( new Category("undefined"));
                item.setCategory(newCategory);
            }
            else{ item.setCategory(defaultCategory); }
        }
        Item newItem = itemService.addItem(item);
        return new ResponseEntity<>(item, HttpStatus.CREATED); //ok is 200 status code
    }

//    @PutMapping("/addImage/{id}")
//    public void uploadImage(@RequestParam("file") MultipartFile image, @PathVariable("id") Long id) {
//        try {
//            byte[] img = image.getBytes();
//            System.out.println(img);
//            itemService.insertImage(image, id);
//            System.out.println("Image saved");
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }

        @PutMapping("/update")
    public ResponseEntity<Item> updateItem(@RequestBody Item item)
    {
        Item updateItem = itemService.updateItem(item);
        return new ResponseEntity<>(updateItem, HttpStatus.OK);  //ok is 200 status code
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable("id") Long id)
    {
        itemService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
