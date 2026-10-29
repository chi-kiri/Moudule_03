package com.example.book_management.dto;

import com.example.book_management.validation.ExistingBookId;
import jakarta.validation.constraints.NotBlank;

public class BorrowCreateDTO {

    @NotBlank(message = "Username không được để trống")
    private String username;

    @ExistingBookId
    private Long bookId;

    public BorrowCreateDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}