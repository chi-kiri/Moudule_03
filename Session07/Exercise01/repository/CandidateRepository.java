package com.example.recruit_pro.repository;

import com.example.recruit_pro.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}