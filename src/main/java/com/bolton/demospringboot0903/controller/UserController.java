package com.bolton.demospringboot0903.controller;

import com.bolton.demospringboot0903.Models.Cart;
import com.bolton.demospringboot0903.Models.User;
import com.bolton.demospringboot0903.Service.CartService;
import com.bolton.demospringboot0903.Service.OrderService;
import com.bolton.demospringboot0903.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {
    private final UserService userService;
    private final CartService cartService;
    private final OrderService orderService;

    public UserController(UserService userService, CartService cartService, OrderService orderService) {
        this.userService = userService;
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers()
    {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id)
    {
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK); //ok is 200 status code
    }

    @PostMapping("/getUsersByLastName")
    public ResponseEntity<List<User>> getUsersContainingLastName(@RequestBody User user)
    {
        List<User> users = userService.findAllContainingLastName(user.getLastName());
        return new ResponseEntity<>(users, HttpStatus.OK); //ok is 200 status code
    }

    @PostMapping("/getUsersByEmail")
    public ResponseEntity<List<User>> getUsersContainingEmail(@RequestBody User user)
    {
        List<User> users = userService.findAllContainingEmail(user.getEmail());
        return new ResponseEntity<>(users, HttpStatus.OK); //ok is 200 status code
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) throws Exception {
        User createdUser = userService.addUser(user);
        Cart createdCart = cartService.addCart(new Cart(user.getId()));
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED); //ok is 200 status code
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user)
    {
        User updateUser = userService.updateUser(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);  //ok is 200 status code
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id)
    {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/Login")
    public ResponseEntity<User> getAttemptsContainingEmail(@RequestBody User attempt) throws Exception {
        boolean result = userService.verifyAttempt(attempt);
        if (result != true ){ return null; }
        else{ return new ResponseEntity<>(userService.findByEmail(attempt.getEmail()), HttpStatus.OK);  }
       }


    //    @PutMapping("/ChangePassword")
//    public ResponseEntity<List<Attempt>> getAttemptsContainingEmail(@RequestBody Attempt attempt)
//    {
//        AttemptService attemptService;
//        List<Attempt> attempts = attemptService.findAllContainingEmail(attempt.getEmail());
//        return new ResponseEntity<>(attempts, HttpStatus.OK); //ok is 200 status code
//    }


}
