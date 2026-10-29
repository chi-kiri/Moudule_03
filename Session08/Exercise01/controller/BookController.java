package com.example.book_management.controller;

import com.example.book_management.dto.BookCreateDTO;
import com.example.book_management.model.Book;
import com.example.book_management.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> createBook(
            @ModelAttribute BookCreateDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookService.createBook(dto));
    }
}