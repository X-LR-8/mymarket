package com.example.mymarket.repository;

import com.example.mymarket.model.Item;
import com.example.mymarket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmailAndPassword(String email, String password);
    Optional<User> findByUsername(String name);
    Optional<User> findByEmail(String email);
}
