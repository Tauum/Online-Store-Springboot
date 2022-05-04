package com.bolton.demospringboot0903.Service;

import com.bolton.demospringboot0903.Models.Cart;
import com.bolton.demospringboot0903.Repo.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class CartService {
    private final CartRepo cartRepo;

    @Autowired
    public CartService(CartRepo cartRepository) {
        this.cartRepo = cartRepository;
    }

    public Cart addCart(Cart cart) {
        return cartRepo.save(cart);
    }

    public List<Cart> findAll() {
        return cartRepo.findAll();
    }

    public Cart updateCart(Cart cart) {
       Cart original = findCartByUserId(cart.getUserID());
        if (original != null){
            original.setItems(cart.getItems());

            if (original.userID != null){
                original.setUserID(cart.getUserID());
            }
        }
        return cartRepo.save(original);
    }

    //query method (auto generates method in spring back-backend)
    @Transactional
    public void deleteCart(Long id) {
        cartRepo.deleteCartById(id);
    }

    public Cart findCartById(Long id) {
        return cartRepo.findCartById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not foud with id: " + id));
    }

    public Cart findCartByUserId(Long id) {
        return cartRepo.findCartByUserID(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not foud with id: " + id));
    }
}