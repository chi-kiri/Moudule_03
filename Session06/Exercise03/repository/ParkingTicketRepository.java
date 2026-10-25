package com.example.parking_management.repository;

import com.example.parking_management.model.ParkingTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingTicketRepository
        extends JpaRepository<ParkingTicket, Long> {

    Optional<ParkingTicket>
    findTopByVehicleIdAndCheckOutTimeIsNullOrderByCheckInTimeDesc(Long vehicleId);
}