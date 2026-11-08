package com.example.medicalsuppliesmanagement.repository;

import com.example.medicalsuppliesmanagement.entity.Supply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SupplyRepository extends JpaRepository<Supply, Long> {

    List<Supply> findByIsDeletedFalse();

    List<Supply> findByNameContainingIgnoreCaseAndIsDeletedFalse(String name);

    Optional<Supply> findByIdAndIsDeletedFalse(Long id);
}