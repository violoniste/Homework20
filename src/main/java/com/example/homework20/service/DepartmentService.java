package com.example.homework20.service;

import com.example.homework20.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    List<Employee> getEmployees(int departmentId);
    int getSalarySum(int department);
    int getSalaryMax(int departmentId);
    int getSalaryMin(int departmentId);
    Map<Integer, List<Employee>> getEmployees();
}
