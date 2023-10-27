package com.example.homework20.service;

import com.example.homework20.exceptions.EmployeeNotFoundException;
import com.example.homework20.model.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.homework20.repositories.Employees.employees;

@org.springframework.stereotype.Service
public class DepartmentServiceImpl implements DepartmentService {

    @Override
    public String getEmployeeWithMinSalary(int department) {
        return employees.values().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new)
                .toString();
    }

    @Override
    public String getEmployeeWithMaxSalary(int department) {
        return employees.values().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new)
                .toString();
    }

    @Override
    public String printAllEmployees(int department) {
        StringBuilder builder = new StringBuilder("<b>Сотрудники " + department + " отдела:</b>");
        employees.values().stream()
                .filter(e -> e.getDepartment() == department)
                .forEach(e -> builder.append("<br>").append(e.toStringWithoutDepartment()));
        return builder.toString();
    }

    @Override
    public String printAllEmployeesByDepartments() {
        StringBuilder builder = new StringBuilder();
        getAllDepartments().forEach(d -> builder.append(printAllEmployees(d)).append("<br>"));
        return builder.toString();
    }

    public List<Integer> getAllDepartments() {
        return employees.values().stream()
                .map(Employee::getDepartment)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}
