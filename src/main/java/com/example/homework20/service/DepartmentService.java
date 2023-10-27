package com.example.homework20.service;

public interface DepartmentService {
    String getEmployeeWithMaxSalary(int departmentId);
    String getEmployeeWithMinSalary(int departmentId);
    String printAllEmployees(int departmentId);
    String printAllEmployeesByDepartments();
}
