package com.example.milkteaauth.repository;

import com.example.milkteaauth.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
