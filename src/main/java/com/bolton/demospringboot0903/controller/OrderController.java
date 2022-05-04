package com.bolton.demospringboot0903.controller;

import com.bolton.demospringboot0903.Models.Order;
import com.bolton.demospringboot0903.Service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders()
    {
        List<Order> orders = orderService.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable("id") Long id)
    {
        Order order = orderService.findOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK); //ok is 200 status code
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Order>> getOrdersForUser(@PathVariable("id") Long id)
    {
        List<Order> orders = orderService.findAllOrdersByUserID(id);
        return new ResponseEntity<>(orders, HttpStatus.OK); //ok is 200 status code
    }

    @PostMapping("/getOrdersForDate")
    public ResponseEntity<List<Order>> getOrdersContainingLastName(@RequestBody Order order)
    {
        List<Order> orders = orderService.findAllForDate(order.getGeneratedDate());
        return new ResponseEntity<>(orders, HttpStatus.OK); //ok is 200 status code
    }

    @PostMapping("/getOrdersForAddress")
    public ResponseEntity<List<Order>> getOrdersContainingAddress(@RequestBody Order order)
    {
        List<Order> orders = orderService.findAllContainingAddress(order.getAddress());
        return new ResponseEntity<>(orders, HttpStatus.OK); //ok is 200 status code
    }

    @PostMapping("/add")
    public ResponseEntity<Order> addOrder(@RequestBody Order order)
    {
        orderService.addOrder(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED); //ok is 200 status code
    }

    @PutMapping("/update")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order)
    {
        Order updateOrder = orderService.updateOrder(order);
        return new ResponseEntity<>(updateOrder, HttpStatus.OK);  //ok is 200 status code
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id)
    {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
