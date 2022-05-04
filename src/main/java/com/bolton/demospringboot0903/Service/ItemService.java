package com.bolton.demospringboot0903.Service;

import com.bolton.demospringboot0903.Models.Item;
import com.bolton.demospringboot0903.Repo.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class ItemService {
    private final ItemRepo itemRepo;

    @Autowired
    public ItemService(ItemRepo itemRepository) {
        this.itemRepo = itemRepository;
    }

    public Item addItem(Item item) {
        return itemRepo.save(item);
    }

    public List<Item> findAll() {
        return itemRepo.findAll();
    }

    public Item updateItem(Item item) {
        Item original = findItemById(item.getId());
        if (original != null){
            try { if (item.getName() != null && item.getName() != "" ){ original.setName(item.getName());}}
            catch(Exception e) { }
            try { if (item.getDescription() != null && item.getDescription() != "" ){ original.setDescription(item.getDescription());}}
            catch(Exception e) { }
            try { if (item.getPrice() != null && item.getPrice().toString() != "" ){ original.setPrice(item.getPrice());}}
            catch(Exception e) { }
            try { if (item.getReleaseDate().toString() != null && item.getReleaseDate().toString() != "" ){ original.setReleaseDate(item.getReleaseDate());}}
            catch(Exception e) { }
            try { if (item.getAmount() != null ){ original.setReleaseDate(item.getReleaseDate());}}
            catch(Exception e) { }
            try { if (item.getStockCount() != null ){ original.setStockCount(item.getStockCount());}}
            catch(Exception e) { }
            try { if (item.getCategory()!= null && item.category.getName() != "" ){ original.setCategory(item.getCategory());}}
            catch(Exception e) { }
//            try { if (item.getImage()!= null){ original.setImage(item.getImage());}}
//            catch(Exception e) { }
        }
        return itemRepo.save(original);
    }

    //query method (auto generates method in spring back-backend)
    @Transactional
    public void deleteItem(Long id) {
        itemRepo.deleteItemById(id);
    }

    public Item findItemById(Long id) {
        return itemRepo.findItemById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
    }

    public List<Item> findAllContainingName(String name) {
        return itemRepo.findItemByNameContains(name);
    }

    public List<Item> findAllContainingCategoryName(String name) {
        return itemRepo.findItemByCategoryNameContains(name);
    }

//    https://stackoverflow.com/questions/24279186/fetch-random-records-using-spring-data-jpa
    public List<Item> findRandom(int amount) {
        List<Item> randomItems = new ArrayList<>();
        List<Item> copy = findAll();
        SecureRandom rand = new SecureRandom();
        for (int i = 0; i < Math.min(amount, copy.size()); i++) {
            randomItems.add( copy.remove( rand.nextInt( copy.size())));
        }

        return randomItems;
    }


    public Item[][] FindRandomForHome() {
        List<Item> copy = findAll();
        Collections.shuffle(copy);
        System.out.println(copy);

        Random r = new Random();
        int row = 4;
        int column = r.nextInt(4 + 1 - 1) + 1;

        Item matrix [][] = new Item[row][column];
        for(int i = 0; i< matrix.length;i++){

            for (int j = 0; j < (r.nextInt(matrix[i].length + 1 - 1) + 1);j++) {
                matrix[i][j]  = copy.get(0);
                copy.remove(0);
                System.out.print(matrix[i][j]+ " ");
            }
        }
        return matrix;
    }



//    public Item insertImage(MultipartFile imageFile, Long id) throws IOException {
//        Item existing  = findItemById(id);
//        if (existing != null){
//            existing.setImage(imageFile.getBytes());
//            return itemRepo.save(existing);
//        }
//        return existing;
//    }
}