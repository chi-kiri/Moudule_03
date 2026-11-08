package com.example.medicalsuppliesmanagement.dto.response;

public class TopExportResponse {

    private String topSupplyName;
    private Long totalExportQuantity;

    public TopExportResponse(
            String topSupplyName,
            Long totalExportQuantity
    ) {
        this.topSupplyName = topSupplyName;
        this.totalExportQuantity = totalExportQuantity;
    }

    public String getTopSupplyName() {
        return topSupplyName;
    }

    public Long getTotalExportQuantity() {
        return totalExportQuantity;
    }
}