package com.example.milkteaauth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class OrderResponse {

    private Long id;
    private String customerEmail;
    private LocalDateTime createdDate;
    private String status;
    private BigDecimal totalMoney;
    private List<OrderItemResponse> items;
}
