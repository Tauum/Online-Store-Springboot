package com.bolton.demospringboot0903.Repo;

import com.bolton.demospringboot0903.Models.Address;
import com.bolton.demospringboot0903.Models.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepo extends JpaRepository<Order, Long> {

    void deleteOrderById(Long id);
    Optional<Order> findOrderById(Long id);

    List<Order> findOrderByGeneratedDateContains(LocalDate generatedDate);

    @EntityGraph(attributePaths = {"address"})
    List<Order> findOrderByAddressContains(Address address);

    Optional<List<Order>> findAllOrderByUserID(Long id);
}
