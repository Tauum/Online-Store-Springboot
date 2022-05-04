package com.bolton.demospringboot0903.Models;


import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Table(name="Orders")
@Entity
public class Order {

    @Id
    @GeneratedValue
    public Long id;
    public double totalItemPice;
    public double concurrantTotal;
    public double shipping;
    public double discount;
    public LocalDate generatedDate;
    public String firstName;
    public String lastName;

    @Nullable
    public Long userID;

    @OneToOne(targetEntity = Address.class, cascade=CascadeType.ALL)
    @JoinTable(name = "Order_address",
            joinColumns = { @JoinColumn(name = "order_id")},
            inverseJoinColumns = { @JoinColumn(name = "address_id")})
    public Address address;

    @ManyToMany(targetEntity = Item.class)
    @JoinTable(name = "Order_item",
            joinColumns = { @JoinColumn(name = "order_id")},
            inverseJoinColumns = { @JoinColumn(name = "item_id")})
    public List<Item> items;

    public Order() { }

    public Order(double totalItemPice, double concurrantTotal, double shipping, double discount, LocalDate generatedDate, String firstName, String lastName, @Nullable Long userID, Address address, List<Item> items) {
        this.totalItemPice = totalItemPice;
        this.concurrantTotal = concurrantTotal;
        this.shipping = shipping;
        this.discount = discount;
        this.generatedDate = generatedDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = userID;
        this.address = address;
        this.items = items;
    }

    public Order(Long id, double totalItemPice, double concurrantTotal, double shipping, double discount, LocalDate generatedDate, String firstName, String lastName, @Nullable Long userID, Address address, List<Item> items) {
        this.id = id;
        this.totalItemPice = totalItemPice;
        this.concurrantTotal = concurrantTotal;
        this.shipping = shipping;
        this.discount = discount;
        this.generatedDate = generatedDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = userID;
        this.address = address;
        this.items = items;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Nullable
    public Long getUserID() {
        return userID;
    }

    public void setUserID(@Nullable Long userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getTotalItemPice() {
        return totalItemPice;
    }

    public void setTotalItemPice(double totalItemPice) {
        this.totalItemPice = totalItemPice;
    }

    public double getConcurrantTotal() {
        return concurrantTotal;
    }

    public void setConcurrantTotal(double concurrantTotal) {
        this.concurrantTotal = concurrantTotal;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getShipping() {
        return shipping;
    }

    public void setShipping(double shipping) {
        this.shipping = shipping;
    }

    public LocalDate getGeneratedDate() {
        return generatedDate;
    }

    public void setGeneratedDate(LocalDate generatedDate) {
        this.generatedDate = generatedDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
