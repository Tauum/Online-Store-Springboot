package com.bolton.demospringboot0903;

import com.bolton.demospringboot0903.Models.Category;
import com.bolton.demospringboot0903.Repo.CategoryRepo;
import com.bolton.demospringboot0903.Service.CategoryService;
import com.bolton.demospringboot0903.controller.CategoryController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CategoryTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryController categoryController;

    @MockBean
    private CategoryRepo categoryRepo;

    @Test
    public void create(){
        Category category = new Category(1L, "test");
        when(categoryRepo.save(category)).thenReturn(category);
        assertEquals(category, categoryRepo.save(category));
    }

    @Test
    public void findAll(){

        when(categoryRepo.findAll()).thenReturn(Stream
                .of(new Category(1L,"test1"), new Category(2L, "test2")).collect(Collectors.toList()));
        assertEquals(2, categoryRepo.findAll().size());

//
//        when(categoryRepo.findAll()).thenReturn(Stream
//                .of(new Category(1L,"test1"), new Category(2L, "test2")).collect(Collectors.toList()));
//
//        assertEquals(2, categoryRepo.findAll().size());
//
//        when(categoryRepo.findAll()).thenReturn(Stream
//                .of(new Category(1L,"test1"), new Category(2L, "test2"))
//                .collect(Collectors.toList()));
//
//        assertEquals(2, categoryRepo.findAll().size());
//
//        List<Category> categories = categoryRepo.findAll();
//
//        for (Category category : categories){
//            System.out.println(category.toString());
//        }

//        assertEquals(category1, categoryRepo.findAll().get(0));

    }


}
