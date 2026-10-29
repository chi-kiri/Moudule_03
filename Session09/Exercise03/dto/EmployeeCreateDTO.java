package com.example.hrm_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class EmployeeCreateDTO {

    private String fullName;

    @Email(message = "Email không đúng định dạng")
    private String email;

    @Pattern(
            regexp = "^0(3|5|7|8|9)[0-9]{8}$",
            message = "Số điện thoại không hợp lệ"
    )
    private String phone;

    @Min(value = 5000000, message = "Lương phải từ 5000000 trở lên")
    private Long salary;

    @NotNull(message = "Phòng ban không được để trống")
    private Long departmentId;

    public EmployeeCreateDTO() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}