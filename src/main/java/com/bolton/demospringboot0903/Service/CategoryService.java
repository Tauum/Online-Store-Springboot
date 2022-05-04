package com.bolton.demospringboot0903.Service;

import com.bolton.demospringboot0903.Models.Category;
import com.bolton.demospringboot0903.Repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {
    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryService(CategoryRepo categoryRepository) {
        this.categoryRepo = categoryRepository;
    }

    public Category addCategory(Category category) {
        return categoryRepo.save(category);
    }

    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    public Category findDefault(String defaultCategory) {

        return categoryRepo.findCategoryByNameEquals(defaultCategory);
    }

    public Category updateCategory(Category category) {
        Category original = findCategoryById(category.getId());
        if (original != null ){
            try { if (category.getName() != null && category.getName() != "" ){ original.setName(category.getName());}}
            catch(Exception e) { }
        }
        return categoryRepo.save(original);
    }

    //query method (auto generates method in spring back-backend)
    @Transactional
    public void deleteCategory(Long id) {
        categoryRepo.deleteCategoryById(id);
    }

    public Category findCategoryById(Long id) {
        return categoryRepo.findCategoryById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not foud with id: " + id));
    }

    public List<Category> findAllContainingName(String Name) {
        return categoryRepo.findCategoryByNameContains(Name);
    }
}