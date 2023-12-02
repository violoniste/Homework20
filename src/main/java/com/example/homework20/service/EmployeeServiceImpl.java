package com.example.homework20.service;

import com.example.homework20.exceptions.BadRequestException;
import com.example.homework20.exceptions.EmployeeAlreadyAddedException;
import com.example.homework20.exceptions.EmployeeNotFoundException;
import com.example.homework20.exceptions.EmployeeStorageIsFullException;
import com.example.homework20.model.Employee;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public class EmployeeServiceImpl implements EmployeeService {
    final int MAX = 15;
    private final Map<String, Employee> employees = new HashMap<>();

    @Override
    public Employee add(String firstName, String lastName, int department, int salary) {
        check(firstName);
        check(lastName);

        firstName = StringUtils.capitalize(firstName);
        lastName = StringUtils.capitalize(lastName);

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
        check(firstName);
        check(lastName);

        Employee employee = getEmployee(firstName, lastName);
        if (employee == null)
            throw new EmployeeNotFoundException();

        employees.remove(getKey(firstName, lastName));
        return employee;
    }

    @Override
    public Employee find(String firstName, String lastName) {
        check(firstName);
        check(lastName);

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
        check(firstName);
        check(lastName);

        return employees.get(getKey(firstName, lastName));
    }

    private String getKey(String firstName, String lastName) {
        return firstName + lastName;
    }

    private void check(String str) {
        if (!StringUtils.isAlpha(str))
            throw new BadRequestException();
    }
}
