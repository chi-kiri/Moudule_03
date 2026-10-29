package com.example.book_management.controller;

import com.example.book_management.dto.BorrowCreateDTO;
import com.example.book_management.model.Borrow;
import com.example.book_management.service.BorrowService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/borrows")
public class BorrowController {

    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping
    public ResponseEntity<Borrow> createBorrow(
            @Valid @RequestBody BorrowCreateDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(borrowService.createBorrow(dto));
    }
}