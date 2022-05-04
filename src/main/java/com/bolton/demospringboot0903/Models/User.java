package com.bolton.demospringboot0903.Models;

import javax.persistence.*;
import java.util.List;
import java.util.List;
import java.util.UUID;

@Entity

public class User implements java.io.Serializable {

    @Id
    @GeneratedValue
    public Long id;
    private String email;
    private String password;
    public String firstName;
    public String lastName;

    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "User_address",
            joinColumns = { @JoinColumn(name = "user_id")},
            inverseJoinColumns = { @JoinColumn(name = "address_id")})
    public Address address;

    public User() { }

    public User(String email, String password, String firstName, String lastName, Role role, Address address)
    {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.address = address;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
