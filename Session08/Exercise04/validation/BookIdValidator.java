package com.example.book_management.validation;

import com.example.book_management.repository.BookRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class BookIdValidator
        implements ConstraintValidator<ExistingBookId, Long> {

    private final BookRepository bookRepository;

    public BookIdValidator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public boolean isValid(Long bookId,
                           ConstraintValidatorContext context) {
        return bookId != null && bookRepository.existsById(bookId);
    }
}