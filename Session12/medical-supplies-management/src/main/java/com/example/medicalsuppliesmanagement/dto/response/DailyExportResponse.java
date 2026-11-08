package com.example.medicalsuppliesmanagement.dto.response;

public class DailyExportResponse {

    private Long supplyId;
    private String supplyName;
    private Long totalExportQuantity;

    public DailyExportResponse(
            Long supplyId,
            String supplyName,
            Long totalExportQuantity
    ) {
        this.supplyId = supplyId;
        this.supplyName = supplyName;
        this.totalExportQuantity = totalExportQuantity;
    }

    public Long getSupplyId() {
        return supplyId;
    }

    public String getSupplyName() {
        return supplyName;
    }

    public Long getTotalExportQuantity() {
        return totalExportQuantity;
    }
}