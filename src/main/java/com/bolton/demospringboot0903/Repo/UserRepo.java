package com.bolton.demospringboot0903.Repo;

import com.bolton.demospringboot0903.Models.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, Long> {

    void deleteUserById(Long id);

    Optional<User> findUserById(Long id);

    //    @Query("SELECT x FROM User x WHERE x.")
    List<User> findUserByLastNameContains(String name);

    List<User> findUserByEmailContains(String email);

    User findUserByEmail(String email);

//    @EntityGraph(attributePaths = {"address", "cart", "orders"})
}
