package com.example.recruit_pro.service;

import com.example.recruit_pro.dto.CandidateCreateDTO;
import com.example.recruit_pro.model.Candidate;
import com.example.recruit_pro.repository.CandidateRepository;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Candidate createCandidate(CandidateCreateDTO dto) {
        Candidate candidate = new Candidate();

        candidate.setFullName(dto.getFullName());
        candidate.setEmail(dto.getEmail());
        candidate.setAge(dto.getAge());
        candidate.setYearsOfExperience(dto.getYearsOfExperience());

        return candidateRepository.save(candidate);
    }
}