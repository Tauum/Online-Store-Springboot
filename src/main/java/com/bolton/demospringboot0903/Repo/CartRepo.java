package com.bolton.demospringboot0903.Repo;

import com.bolton.demospringboot0903.Models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartRepo extends JpaRepository<Cart, Long> {

    void deleteCartById(Long id);
    Optional<Cart> findCartById(Long id);

    Optional<Cart> findCartByUserID(Long id);
}
