package com.example.patientmanagement.controller;

import com.example.patientmanagement.model.Patient;
import com.example.patientmanagement.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public Patient addPatient(@RequestBody Patient patient) {
        log.info(
                "Received request to add patient: {}",
                patient.getName()
        );

        return patientService.addPatient(patient);
    }
}