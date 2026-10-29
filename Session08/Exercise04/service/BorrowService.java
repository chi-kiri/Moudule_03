package com.example.book_management.service;

import com.example.book_management.dto.BorrowCreateDTO;
import com.example.book_management.model.Borrow;
import com.example.book_management.repository.BorrowRepository;
import org.springframework.stereotype.Service;

@Service
public class BorrowService {

    private final BorrowRepository borrowRepository;

    public BorrowService(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    public Borrow createBorrow(BorrowCreateDTO dto) {
        Borrow borrow = new Borrow();

        borrow.setUsername(dto.getUsername());
        borrow.setBookId(dto.getBookId());

        return borrowRepository.save(borrow);
    }
}