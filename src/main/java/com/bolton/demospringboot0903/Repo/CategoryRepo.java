package com.bolton.demospringboot0903.Repo;

import com.bolton.demospringboot0903.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepo extends JpaRepository<Category, Long> {

    void deleteCategoryById(Long id);
    Optional<Category> findCategoryById(Long id);

    //    @Query("SELECT x FROM Category x WHERE x.")
    List<Category> findCategoryByNameContains(String name);

    Category findCategoryByNameEquals(String name);
}
