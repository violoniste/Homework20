package com.example.homework20.service;

import com.example.homework20.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee add(String firstName, String lastName, int department, int salary);
    Employee remove(String firstName, String lastName);
    Employee find(String firstName, String lastName);
    List<Employee> list();
}
