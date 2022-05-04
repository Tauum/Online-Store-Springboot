package com.bolton.demospringboot0903.Service;

import com.bolton.demospringboot0903.Models.Address;
import com.bolton.demospringboot0903.Models.Item;
import com.bolton.demospringboot0903.Models.Order;
import com.bolton.demospringboot0903.Repo.ItemRepo;
import com.bolton.demospringboot0903.Repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepo orderRepo;
    private final ItemService itemService;

    @Autowired
    public OrderService(OrderRepo orderRepository, ItemRepo itemRepo, ItemService itemService) {
        this.itemService = itemService; // points to item service for updating item to reduce stock
        this.orderRepo = orderRepository;
    }

    // untested
    public Order addOrder(Order order) {
        for (Item listElement :  order.getItems()){ // points to item service for reducing stock
            Item dbItem = itemService.findItemById(listElement.getId());
            if (dbItem != null && dbItem.getStockCount()!= null && dbItem.getStockCount() > 0){
                dbItem.setStockCount(dbItem.getStockCount() - listElement.getAmount());
                itemService.updateItem(dbItem);
            }
        }
        return orderRepo.save(order);
    }

    public List<Order> findAll() {
        return orderRepo.findAll();
    }

    public Order updateOrder(Order order) {
        Order original = findOrderById(order.getId());
        if (original != null){
            try { if (String.valueOf(order.getTotalItemPice()) != "" && order.getTotalItemPice() != 0 ){ original.setTotalItemPice(order.getTotalItemPice());}}
            catch(Exception e) { }
            try { if (String.valueOf(order.getTotalItemPice()) != "" && order.getConcurrantTotal() != 0 ){ original.setConcurrantTotal(order.getConcurrantTotal());}}
            catch(Exception e) { }
            try { if (String.valueOf(order.getShipping()) != "" && order.getShipping() != 0 ){ original.setShipping(order.getShipping());}}
            catch(Exception e) { }
            try { if (String.valueOf(order.getDiscount()) != "" && order.getDiscount() != 0 ){ original.setDiscount(order.getDiscount());}}
            catch(Exception e) { }
            try { if (order.getGeneratedDate() != null ){ original.setGeneratedDate(order.getGeneratedDate());}}
            catch(Exception e) { }
            try {
                if (order.getAddress() != null) {
                    try { original.address.setLine1(order.address.getLine1());}
                    catch(Exception e) { }
                    try { original.address.setLine2(order.address.getLine2());}
                    catch(Exception e) { }
                    try { original.address.setLine3(order.address.getLine3());}
                    catch(Exception e) { }
                    try {  original.address.setNumber(order.address.getNumber());}
                    catch(Exception e) { }
                    try {  original.address.setPostCode(order.address.getPostCode());}
                    catch(Exception e) { }
                }
            }
            catch(Exception e) { }
            try { if (order.getItems() != null) { original.setItems(order.getItems()); } }
            catch(Exception e) { }
            try { if (order.getUserID() != null) { original.setUserID(order.getUserID()); } }
            catch(Exception e) { }

        }
        return orderRepo.save(original);
    }

    //query method (auto generates method in spring back-backend)
    @Transactional
    public void deleteOrder(Long id) {
        orderRepo.deleteOrderById(id);
    }

    public Order findOrderById(Long id) {
        return orderRepo.findOrderById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not foud with id: " + id));
    }

    public List<Order> findAllForDate(LocalDate generatedDate) {
        return orderRepo.findOrderByGeneratedDateContains(generatedDate);
    }

    public List<Order> findAllContainingAddress(Address address) {
        return orderRepo.findOrderByAddressContains(address);
    }

    public List<Order> findAllOrdersByUserID(Long id) {
        return orderRepo.findAllOrderByUserID(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not foud with id: " + id));
    }
}