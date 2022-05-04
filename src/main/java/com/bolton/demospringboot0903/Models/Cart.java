package com.bolton.demospringboot0903.Models;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
public class Cart {

        @Id
        @GeneratedValue
        public Long id;

        @OneToMany(targetEntity = Item.class)
        @JoinTable(name = "Cart_items",
                joinColumns = { @JoinColumn(name = "cart_id")},
                inverseJoinColumns = { @JoinColumn(name = "item_id")})
        @Column(name = "items")
        public List<Item> items;

        @Nullable
        public Long userID;

        public Cart() {}

        public Cart(@Nullable Long userID) {
                this.userID = userID;
        }

        public Cart(List<Item> items, @Nullable Long userID) {
                this.items = items;
                this.userID = userID;
        }

        public Cart(Long id, @Nullable Long userID) {
                this.id = id;
                this.userID = userID;
        }

        @Nullable
        public Long getUserID() {
                return userID;
        }

        public void setUserID(@Nullable Long userID) {
                this.userID = userID;
        }

        public List<Item> getItems() {
                return items;
        }

        public void setItems(List<Item> items) {
                this.items = items;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }
}
