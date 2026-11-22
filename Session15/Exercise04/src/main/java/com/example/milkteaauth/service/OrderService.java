package com.example.milkteaauth.service;

import com.example.milkteaauth.dto.*;
import com.example.milkteaauth.entity.*;
import com.example.milkteaauth.repository.OrderRepository;
import com.example.milkteaauth.repository.ProductRepository;
import com.example.milkteaauth.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderService(
            OrderRepository orderRepository,
            UserRepository userRepository,
            ProductRepository productRepository
    ) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public OrderResponse createOrder(String email, CreateOrderRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Không tìm thấy người dùng"
                ));

        Order order = Order.builder()
                .user(user)
                .status("PENDING")
                .totalMoney(BigDecimal.ZERO)
                .build();

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalMoney = BigDecimal.ZERO;

        for (OrderItemRequest itemRequest : request.getItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Không tìm thấy sản phẩm ID " + itemRequest.getProductId()
                    ));

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(itemRequest.getQuantity())
                    .priceBuy(product.getPrice())
                    .build();

            orderItems.add(orderItem);

            BigDecimal itemTotal = product.getPrice()
                    .multiply(BigDecimal.valueOf(itemRequest.getQuantity()));
            totalMoney = totalMoney.add(itemTotal);
        }

        order.setItems(orderItems);
        order.setTotalMoney(totalMoney);

        return toResponse(orderRepository.save(order));
    }

    @Transactional
    public List<OrderResponse> getMyOrders(String email) {
        return orderRepository.findByUserEmailOrderByCreatedDateDesc(email)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDate"))
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public OrderResponse updateStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Không tìm thấy đơn hàng"
                ));

        order.setStatus(status.trim().toUpperCase());
        return toResponse(orderRepository.save(order));
    }

    private OrderResponse toResponse(Order order) {
        List<OrderItemResponse> items = order.getItems()
                .stream()
                .map(item -> new OrderItemResponse(
                        item.getId(),
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getPriceBuy()
                ))
                .toList();

        return new OrderResponse(
                order.getId(),
                order.getUser().getEmail(),
                order.getCreatedDate(),
                order.getStatus(),
                order.getTotalMoney(),
                items
        );
    }
}
