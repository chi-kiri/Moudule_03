package com.example.recruit_pro.controller;

import com.example.recruit_pro.dto.CandidateCreateDTO;
import com.example.recruit_pro.model.Candidate;
import com.example.recruit_pro.service.CandidateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping
    public ResponseEntity<Candidate> createCandidate(
            @Valid @RequestBody CandidateCreateDTO dto) {

        Candidate candidate = candidateService.createCandidate(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(candidate);
    }
}