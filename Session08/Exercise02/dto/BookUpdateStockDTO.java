package com.example.book_management.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class BookUpdateStockDTO {

    @NotNull(message = "Stock không được để trống")
    @Min(value = 0, message = "Stock không được nhỏ hơn 0")
    private Integer stock;

    public BookUpdateStockDTO() {
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}