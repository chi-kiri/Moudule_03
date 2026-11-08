package com.example.medicalsuppliesmanagement.exception;

import com.example.medicalsuppliesmanagement.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<Void>> handleBadRequest(
            BadRequestException e
    ) {
        log.error("Bad Request: {}", e.getMessage(), e);

        ApiResponse<Void> response = new ApiResponse<>(
                "ERROR",
                400,
                e.getMessage(),
                null
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
        log.error("Lỗi hệ thống xảy ra: ", e);

        ApiResponse<Void> response = new ApiResponse<>(
                "ERROR",
                500,
                "Đã xảy ra lỗi hệ thống",
                null
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(
            ResourceNotFoundException e
    ) {
        log.error("Not Found: {}", e.getMessage(), e);

        ApiResponse<Void> response = new ApiResponse<>(
                "ERROR",
                404,
                e.getMessage(),
                null
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }
}