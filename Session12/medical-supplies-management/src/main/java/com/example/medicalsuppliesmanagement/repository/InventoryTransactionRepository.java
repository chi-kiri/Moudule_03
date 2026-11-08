package com.example.medicalsuppliesmanagement.repository;

import com.example.medicalsuppliesmanagement.dto.response.DailyExportResponse;
import com.example.medicalsuppliesmanagement.entity.InventoryTransaction;
import com.example.medicalsuppliesmanagement.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDateTime;
import java.util.List;

public interface InventoryTransactionRepository
        extends JpaRepository<InventoryTransaction, Long> {

    @Query("""
            SELECT new com.example.medicalsuppliesmanagement.dto.response.DailyExportResponse(
                t.supply.id,
                t.supply.name,
                SUM(t.amount)
            )
            FROM InventoryTransaction t
            WHERE t.type = :type
              AND t.createdAt >= :start
              AND t.createdAt < :end
            GROUP BY t.supply.id, t.supply.name
            """)
    List<DailyExportResponse> findDailyExportStatistics(
            @Param("type") TransactionType type,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    @Query("""
        SELECT t.supply.name, SUM(t.amount)
        FROM InventoryTransaction t
        WHERE t.type = :type
        GROUP BY t.supply.id, t.supply.name
        ORDER BY SUM(t.amount) DESC
        """)
    List<Object[]> findTopExport(
            @Param("type") TransactionType type
    );
}