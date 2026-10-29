package com.example.book_management.service;

import com.example.book_management.dto.BookCreateDTO;
import com.example.book_management.dto.BookUpdateStockDTO;
import com.example.book_management.exception.ResourceNotFoundException;
import com.example.book_management.model.Book;
import com.example.book_management.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(BookCreateDTO dto) {
        try {
            Path uploadDir = Paths.get("uploads");

            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            String originalFilename = dto.getCoverImage().getOriginalFilename();
            String fileName = UUID.randomUUID() + "_" + originalFilename;

            Path filePath = uploadDir.resolve(fileName);
            dto.getCoverImage().transferTo(filePath);

            Book book = new Book();
            book.setTitle(dto.getTitle());
            book.setAuthor(dto.getAuthor());
            book.setStock(dto.getStock());
            book.setCoverUrl("uploads/" + fileName);

            return bookRepository.save(book);

        } catch (IOException e) {
            throw new RuntimeException("Không thể lưu file ảnh");
        }
    }

    public Book updateBook(Long id, BookUpdateStockDTO dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Không tìm thấy sách"));

        book.setStock(dto.getStock());

        return bookRepository.save(book);
    }
}