package com.example.patientmanagement.exception;

import com.example.patientmanagement.response.ApiResponseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseError> handleException(Exception e) {

        log.error("Lỗi hệ thống xảy ra: ", e);

        ApiResponseError response = new ApiResponseError(
                "ERROR",
                500,
                "Đã xảy ra lỗi hệ thống"
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}