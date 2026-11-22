package com.example.milkteaauth.repository;

import com.example.milkteaauth.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserEmailOrderByCreatedDateDesc(String email);
}
