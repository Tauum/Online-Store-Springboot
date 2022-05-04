package com.bolton.demospringboot0903.Models;

import javax.persistence.*;
import java.awt.Image;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
public class Item {

    @Id
    @GeneratedValue
    public Long id;
    public String name;
    public String description;
    public LocalDate releaseDate;
    public Double price;
    public int score;
    public Integer amount;
    public Integer stockCount;

//https://www.youtube.com/watch?v=5EbmzsvPMCs https://www.pixeltrice.com/image-gallery-spring-boot-application-using-mysql-and-thymeleaf/
    @Lob
    @Column(length = Integer.MAX_VALUE, nullable = true)
    public byte[] image;

    @ManyToOne(targetEntity = Category.class)
    @JoinTable(name = "Item_category",
            joinColumns = { @JoinColumn(name = "item_id")},
            inverseJoinColumns = { @JoinColumn(name = "category_id")})
    public Category category;

    public Item() { }

    public Item(String name, String description, Double price, LocalDate releaseDate, int score, Integer amount, Integer stockCount, byte[] image, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.releaseDate = releaseDate;
        this.score = score;
        this.amount = amount;
        this.stockCount = stockCount;
        this.image = image;
        this.category = category;
    }

    public Item(Long id, String name, String description, LocalDate releaseDate, Double price, int score, Integer amount, Integer stockCount, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.price = price;
        this.score = score;
        this.amount = amount;
        this.stockCount = stockCount;
        this.category = category;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
