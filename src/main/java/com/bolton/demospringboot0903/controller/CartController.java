package com.bolton.demospringboot0903.controller;

import com.bolton.demospringboot0903.Models.Cart;
import com.bolton.demospringboot0903.Service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts()
    {
        List<Cart> carts = cartService.findAll();
        return new ResponseEntity<>(carts, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable("id") Long id)
    {
        Cart cart = cartService.findCartById(id);
        return new ResponseEntity<>(cart, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Cart> getCartByUserID(@PathVariable("id") Long id)
    {
        Cart cart = cartService.findCartByUserId(id);
        return new ResponseEntity<>(cart, HttpStatus.OK); //ok is 200 status code
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addCart(@RequestBody Cart cart)
    {
        Cart newCart = cartService.addCart(cart);
        return new ResponseEntity<>(cart, HttpStatus.CREATED); //ok is 200 status code
    }

    @PutMapping("/update")
    public ResponseEntity<Cart> updateCart(@RequestBody Cart cart)
    {
        Cart updateCart = cartService.updateCart(cart);
        return new ResponseEntity<>(updateCart, HttpStatus.OK);  //ok is 200 status code
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable("id") Long id)
    {
        cartService.deleteCart(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
