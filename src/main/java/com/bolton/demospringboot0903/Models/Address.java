package com.bolton.demospringboot0903.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Address {

    @Id
    @GeneratedValue
    public Long id;

    public Integer number;
    public String line1;
    public String line2;
    public String line3;
    public String postCode;

    public Address() { }

    public Address(Integer number, String line1, String line2, String line3, String postCode) {
        this.number = number;
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.postCode = postCode;
    }

    public Address(Long id, Integer number, String line1, String line2, String line3, String postCode) {
        this.id = id;
        this.number = number;
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.postCode = postCode;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getLine3() {
        return line3;
    }

    public void setLine3(String line3) {
        this.line3 = line3;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
