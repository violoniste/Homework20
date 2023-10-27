package com.example.homework20.service;

public interface EmployeeService {
    void populate();
    String add(String firstName, String lastName, int department, int salary);
    String remove(String firstName, String lastName);
    String find(String firstName, String lastName);
    String getEmployeeWithMaxSalary(int departmentId);
    String list();
    String getEmployeeWithMinSalary(int departmentId);
    String printAllEmployees(int departmentId);
    String printAllEmployeesByDepartments();
}
