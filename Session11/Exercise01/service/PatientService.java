package com.example.patientmanagement.service;

import com.example.patientmanagement.model.Patient;

import com.example.patientmanagement.model.Patient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PatientService {

    public Patient addPatient(Patient patient) {

        log.info("Adding patient: {}", patient.getName());

        if (patient.getAge() > 120) {
            log.warn(
                    "Patient {} has unusually high age: {}",
                    patient.getName(),
                    patient.getAge()
            );
        }

        return patient;
    }
}