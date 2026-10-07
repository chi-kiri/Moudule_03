package com.example.product_management.service;

import com.example.product_management.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final List<Product> products = List.of(
            new Product(1, "Laptop", 15000000),
            new Product(2, "Mouse", 300000),
            new Product(3, "Keyboard", 500000)
    );

    public List<Product> getAllProducts() {
        return products;
    }
}