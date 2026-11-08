package com.example.medicalsuppliesmanagement.dto.response;

public class SupplyResponse {

    private Long id;
    private String name;
    private String specification;
    private String provider;
    private String unit;
    private Integer quantity;

    public SupplyResponse() {
    }

    public SupplyResponse(
            Long id,
            String name,
            String specification,
            String provider,
            String unit,
            Integer quantity
    ) {
        this.id = id;
        this.name = name;
        this.specification = specification;
        this.provider = provider;
        this.unit = unit;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecification() {
        return specification;
    }

    public String getProvider() {
        return provider;
    }

    public String getUnit() {
        return unit;
    }

    public Integer getQuantity() {
        return quantity;
    }
}