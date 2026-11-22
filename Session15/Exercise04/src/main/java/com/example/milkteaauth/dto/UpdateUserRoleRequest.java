package com.example.milkteaauth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UpdateUserRoleRequest(
        @NotBlank
        @Pattern(regexp = "(?:ROLE_)?(?:STAFF|CUSTOMER)")
        String role
) {
    public String authority() {
        return role.startsWith("ROLE_") ? role : "ROLE_" + role;
    }
}
