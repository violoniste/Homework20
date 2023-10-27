package com.example.homework20.controller;

import com.example.homework20.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService service) {
        this.employeeService = service;
    }

    @GetMapping(path = "/employee/add")
    public String add(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("department") int department, @RequestParam("salary") int salary) {
        return employeeService.add(firstName, lastName, department, salary);
    }

    @GetMapping(path = "/employee/remove")
    public String remove(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return employeeService.remove(firstName, lastName);
    }

    @GetMapping(path = "/employee/find")
    public String find(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return employeeService.find(firstName, lastName);
    }

    @GetMapping(path = "/employee/list")
    public String list() {
        return employeeService.list();
    }

    @GetMapping(path = "/departments/max-salary")
    public String getEmployeeWithMaxSalary(@RequestParam("departmentId") int departmentId) {
        return employeeService.getEmployeeWithMaxSalary(departmentId);
    }

    @GetMapping(path = "/departments/min-salary")
    public String getEmployeeWithMinSalary(@RequestParam("departmentId") int departmentId) {
        return employeeService.getEmployeeWithMinSalary(departmentId);
    }

    @GetMapping(path = "/departments/all")
    public String printAllEmployees(@RequestParam(name = "departmentId", required = false) Integer departmentId) {
        if (departmentId != null)
            return employeeService.printAllEmployees(departmentId);
        else
            return employeeService.printAllEmployeesByDepartments();
    }
}
