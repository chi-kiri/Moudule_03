package com.example.employeesecurity.model;

public class Employee {

    private Long id;
    private String fullName;
    private double salary;

    public Employee() {
    }

    public Employee(Long id, String fullName, double salary) {
        this.id = id;
        this.fullName = fullName;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public double getSalary() {
        return salary;
    }
}
