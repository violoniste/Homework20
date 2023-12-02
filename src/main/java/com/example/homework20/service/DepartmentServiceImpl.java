package com.example.homework20.service;

import com.example.homework20.exceptions.EmployeeNotFoundException;
import com.example.homework20.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public List<Employee> getEmployees(int department) {
        return employeeService.list().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public int getSalarySum(int department) {
        return employeeService.list().stream()
                .filter(e -> e.getDepartment() == department)
                .mapToInt(Employee::getSalary)
                .sum();
    }

    @Override
    public int getSalaryMin(int department) {
        return employeeService.list().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new)
                .getSalary();
    }

    @Override
    public int getSalaryMax(int department) {
        return employeeService.list().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new)
                .getSalary();
    }

    @Override
    public Map<Integer, List<Employee>> getEmployees() {
        return employeeService.list().stream().collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
