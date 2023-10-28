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
    public Map<Integer, ArrayList<Employee>> getAllEmployeesByDepartments() {
        HashMap<Integer, ArrayList<Employee>> map = new HashMap<>();
        getAllDepartments().forEach(d -> map.put(d, new ArrayList<>()));
        employeeService.list().forEach(e -> map.get(e.getDepartment()).add(e));
        return map;
    }

    public List<Integer> getAllDepartments() {
        return employeeService.list().stream()
                .map(Employee::getDepartment)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}
