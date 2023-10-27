package com.example.homework20.controller;

import com.example.homework20.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {
    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping(path = "/max-salary")
    public String getEmployeeWithMaxSalary(@RequestParam("departmentId") int departmentId) {
        return service.getEmployeeWithMaxSalary(departmentId);
    }

    @GetMapping(path = "/min-salary")
    public String getEmployeeWithMinSalary(@RequestParam("departmentId") int departmentId) {
        return service.getEmployeeWithMinSalary(departmentId);
    }

    @GetMapping(path = "/all")
    public String printAllEmployees(@RequestParam(name = "departmentId", required = false) Integer departmentId) {
        if (departmentId != null)
            return service.printAllEmployees(departmentId);
        else
            return service.printAllEmployeesByDepartments();
    }
}
