package com.example.homework20.service;

import com.example.homework20.exceptions.EmployeeAlreadyAddedException;
import com.example.homework20.exceptions.EmployeeNotFoundException;
import com.example.homework20.exceptions.EmployeeStorageIsFullException;
import com.example.homework20.model.Employee;

import static com.example.homework20.repositories.Employees.MAX;
import static com.example.homework20.repositories.Employees.employees;

@org.springframework.stereotype.Service
public class EmployeeServiceImpl implements EmployeeService {

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
    public String add(String firstName, String lastName, int department, int salary) {
        if (employees.size() >= MAX)
            throw new EmployeeStorageIsFullException();

        if (getEmployee(firstName, lastName) != null)
            throw new EmployeeAlreadyAddedException();

        Employee employee = new Employee(firstName, lastName, department, salary);
        employees.put(getKey(firstName, lastName), employee);
        return employee.toString();
    }

    @Override
    public String remove(String firstName, String lastName) {
        Employee employee = getEmployee(firstName, lastName);
        if (employee == null)
            throw new EmployeeNotFoundException();

        employees.remove(getKey(firstName, lastName));
        return employee.toString();
    }

    @Override
    public String find(String firstName, String lastName) {
        Employee employee = getEmployee(firstName, lastName);
        if (employee == null)
            throw new EmployeeNotFoundException();

        return employee.toString();
    }

    @Override
    public String list() {
        StringBuilder builder = new StringBuilder("<b>Сотрудники:</b>");
        employees.values().forEach(e -> builder.append("<br>").append(e.toString()));
        return builder.toString();
    }

    public Employee getEmployee(String firstName, String lastName) {
        return employees.get(getKey(firstName, lastName));
    }

    private String getKey(String firstName, String lastName) {
        return firstName + lastName;
    }
}
