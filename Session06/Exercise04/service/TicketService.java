package com.example.parking_management.service;

import com.example.parking_management.dto.TicketSummaryResponse;
import com.example.parking_management.repository.ParkingTicketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    private final ParkingTicketRepository parkingTicketRepository;

    public TicketService(ParkingTicketRepository parkingTicketRepository) {
        this.parkingTicketRepository = parkingTicketRepository;
    }

    public List<TicketSummaryResponse> getTodayTicketSummary() {

        LocalDateTime startTime =
                LocalDate.now().atStartOfDay();

        LocalDateTime endTime =
                LocalDate.now().plusDays(1).atStartOfDay();

        return parkingTicketRepository.findTodayTicketSummary(
                startTime,
                endTime
        );
    }
}