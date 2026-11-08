package com.example.patientmanagement.service;

import com.example.patientmanagement.model.Patient;
import com.example.patientmanagement.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient addPatient(Patient patient) {
        log.info("Adding patient: {}", patient.getName());

        if (patient.getAge() > 120) {
            log.warn(
                    "Patient {} has unusually high age: {}",
                    patient.getName(),
                    patient.getAge()
            );
        }

        return patientRepository.save(patient);
    }

    public List<Patient> searchPatients(String name) {
        log.trace("Searching patients with name: {}", name);

        return patientRepository.findByNameContainingIgnoreCase(name);
    }
}