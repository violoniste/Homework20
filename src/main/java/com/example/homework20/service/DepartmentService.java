package com.example.homework20.service;

import com.example.homework20.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee getEmployeeWithMaxSalary(int departmentId);
    Employee getEmployeeWithMinSalary(int departmentId);
    List<Employee> getAllEmployees(int departmentId);
    Map<Integer, ArrayList<Employee>> getAllEmployeesByDepartments();
}
