package com.bolton.demospringboot0903.Repo;

import com.bolton.demospringboot0903.Models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemRepo extends JpaRepository<Item, Long> {

    void deleteItemById(Long id);
    Optional<Item> findItemById(Long id);

    //    @Query("SELECT x FROM Item x WHERE x.")
    List<Item> findItemByNameContains(String name);

    List<Item> findItemByCategoryNameContains(String name);
}
