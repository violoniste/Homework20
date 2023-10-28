package com.example.homework20.service;

import com.example.homework20.exceptions.EmployeeAlreadyAddedException;
import com.example.homework20.exceptions.EmployeeNotFoundException;
import com.example.homework20.exceptions.EmployeeStorageIsFullException;
import com.example.homework20.model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public class EmployeeServiceImpl implements EmployeeService {
    final int MAX = 15;
    private final Map<String, Employee> employees = new HashMap<>();

    @Override
    public void populate() {
        add("Иван", "Иванович", 1, 5000);
        add("Василий", "Петрович", 4, 2000);
        add("Михаил", "Васильевич", 3, 7000);
        add("Клубника", "Николаевна", 2, 7000);
        add("Вишня", "Владимировна", 3, 4000);
        add("Курага", "Петровна", 2, 8000);
        add("Чернослив", "Михайлович", 1, 5000);
        add("Миндаль", "Иванович", 5, 3000);
        add("Фундук", "Петрович", 2, 7000);
        add("Вальдемар", "Иванович", 1, 300000);
    }

    @Override
    public Employee add(String firstName, String lastName, int department, int salary) {
        if (employees.size() >= MAX)
            throw new EmployeeStorageIsFullException();

        if (getEmployee(firstName, lastName) != null)
            throw new EmployeeAlreadyAddedException();

        Employee employee = new Employee(firstName, lastName, department, salary);
        employees.put(getKey(firstName, lastName), employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = getEmployee(firstName, lastName);
        if (employee == null)
            throw new EmployeeNotFoundException();

        employees.remove(getKey(firstName, lastName));
        return employee;
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = getEmployee(firstName, lastName);
        if (employee == null)
            throw new EmployeeNotFoundException();

        return employee;
    }

    @Override
    public List<Employee> list() {
        return new ArrayList<>(employees.values());
    }

    public Employee getEmployee(String firstName, String lastName) {
        return employees.get(getKey(firstName, lastName));
    }

    private String getKey(String firstName, String lastName) {
        return firstName + lastName;
    }
}
