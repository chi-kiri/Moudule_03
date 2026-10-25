package com.example.parking_management.controller;

import com.example.parking_management.dto.TicketRequest;
import com.example.parking_management.dto.TicketResponse;
import com.example.parking_management.dto.TicketSummaryResponse;
import com.example.parking_management.response.ApiResponse;
import com.example.parking_management.service.ParkingService;
import com.example.parking_management.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
public class ParkingTicketController {

    private final ParkingService parkingService;
    private final TicketService ticketService;

    public ParkingTicketController(
            ParkingService parkingService,
            TicketService ticketService) {
        this.parkingService = parkingService;
        this.ticketService = ticketService;
    }

    @PostMapping("/check-in")
    public ResponseEntity<ApiResponse<TicketResponse>> checkIn(
            @RequestBody TicketRequest req) {

        try {
            TicketResponse result = parkingService.checkIn(req);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(
                            new ApiResponse<>(
                                    true,
                                    "Check-in thành công",
                                    result
                            )
                    );

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(
                            new ApiResponse<>(
                                    false,
                                    e.getMessage(),
                                    null
                            )
                    );
        }
    }

    @PutMapping("/check-out/{vehicleId}")
    public ResponseEntity<ApiResponse<TicketResponse>> checkOut(
            @PathVariable Long vehicleId) {

        try {
            TicketResponse result = parkingService.checkOut(vehicleId);

            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Check-out thành công",
                            result
                    )
            );

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(
                            new ApiResponse<>(
                                    false,
                                    e.getMessage(),
                                    null
                            )
                    );
        }
    }

    @GetMapping("/summary")
    public ResponseEntity<ApiResponse<List<TicketSummaryResponse>>> summary() {

        List<TicketSummaryResponse> result =
                ticketService.getTodayTicketSummary();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Lấy danh sách vé trong ngày thành công",
                        result
                )
        );
    }
}