package com.example.parking_management.service;

import com.example.parking_management.dto.TicketRequest;
import com.example.parking_management.dto.TicketResponse;
import com.example.parking_management.model.ParkingTicket;
import com.example.parking_management.model.Vehicle;
import com.example.parking_management.model.Zone;
import com.example.parking_management.repository.ParkingTicketRepository;
import com.example.parking_management.repository.VehicleRepository;
import com.example.parking_management.repository.ZoneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ParkingService {

    private final VehicleRepository vehicleRepository;
    private final ZoneRepository zoneRepository;
    private final ParkingTicketRepository parkingTicketRepository;

    public ParkingService(
            VehicleRepository vehicleRepository,
            ZoneRepository zoneRepository,
            ParkingTicketRepository parkingTicketRepository) {
        this.vehicleRepository = vehicleRepository;
        this.zoneRepository = zoneRepository;
        this.parkingTicketRepository = parkingTicketRepository;
    }

    @Transactional
    public TicketResponse checkIn(TicketRequest req) {

        Vehicle vehicle = vehicleRepository.findById(req.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        Zone zone = zoneRepository.findById(req.getZoneId())
                .orElseThrow(() -> new RuntimeException("Zone not found"));

        if (zone.getOccupiedSpots() >= zone.getCapacity()) {
            throw new RuntimeException("Zone is full");
        }

        ParkingTicket ticket = new ParkingTicket();
        ticket.setVehicle(vehicle);
        ticket.setZone(zone);
        ticket.setCheckInTime(LocalDateTime.now());

        zone.setOccupiedSpots(zone.getOccupiedSpots() + 1);

        zoneRepository.save(zone);
        ParkingTicket savedTicket = parkingTicketRepository.save(ticket);

        return toResponse(savedTicket);
    }

    @Transactional
    public TicketResponse checkOut(Long vehicleId) {

        ParkingTicket ticket = parkingTicketRepository
                .findTopByVehicleIdAndCheckOutTimeIsNullOrderByCheckInTimeDesc(vehicleId)
                .orElseThrow(() -> new RuntimeException("Active parking ticket not found"));

        ticket.setCheckOutTime(LocalDateTime.now());

        Zone zone = ticket.getZone();

        if (zone.getOccupiedSpots() > 0) {
            zone.setOccupiedSpots(zone.getOccupiedSpots() - 1);
        }

        zoneRepository.save(zone);
        ParkingTicket savedTicket = parkingTicketRepository.save(ticket);

        return toResponse(savedTicket);
    }

    private TicketResponse toResponse(ParkingTicket ticket) {
        return new TicketResponse(
                ticket.getId(),
                ticket.getVehicle().getLicensePlate(),
                ticket.getZone().getName(),
                ticket.getCheckInTime(),
                ticket.getCheckOutTime()
        );
    }
}