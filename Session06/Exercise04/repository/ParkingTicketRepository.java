package com.example.parking_management.repository;

import com.example.parking_management.dto.TicketSummaryResponse;
import com.example.parking_management.model.ParkingTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ParkingTicketRepository
        extends JpaRepository<ParkingTicket, Long> {

    Optional<ParkingTicket>
    findTopByVehicleIdAndCheckOutTimeIsNullOrderByCheckInTimeDesc(Long vehicleId);

    @Query("""
            SELECT new com.example.parking_management.dto.TicketSummaryResponse(
                t.id,
                t.vehicle.licensePlate,
                t.zone.name,
                t.checkInTime,
                t.checkOutTime
            )
            FROM ParkingTicket t
            WHERE t.checkInTime >= :startTime
              AND t.checkInTime < :endTime
            ORDER BY t.checkInTime DESC
            """)
    List<TicketSummaryResponse> findTodayTicketSummary(
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );
}