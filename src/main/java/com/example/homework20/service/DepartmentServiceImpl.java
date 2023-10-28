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
    public Employee getEmployeeWithMinSalary(int department) {
        return employeeService.list().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Employee getEmployeeWithMaxSalary(int department) {
        return employeeService.list().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public List<Employee> getAllEmployees(int department) {
        return employeeService.list().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployeesByDepartments() {
        return employeeService.list().stream().collect(Collectors.groupingBy(Employee::getDepartment));
    }

    public List<Integer> getAllDepartments() {
        return employeeService.list().stream()
                .map(Employee::getDepartment)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}
