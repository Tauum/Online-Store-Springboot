package com.bolton.demospringboot0903.Models;

public enum Role {
    CUSTOMER ("Customer"),
    ADMIN ("Admin");

    private final String name;

    Role(String name) {
        this.name = name;
    }
}
