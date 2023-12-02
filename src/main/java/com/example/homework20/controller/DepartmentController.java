package com.example.homework20.controller;

import com.example.homework20.model.Employee;
import com.example.homework20.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {
    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getDepartmentEmployees(@PathVariable("id") int departmentId) {
        return service.getEmployees(departmentId);
    }

    @GetMapping("/{id}/salary/sum")
    public int getDepartmentSalarySum(@PathVariable("id") int departmentId) {
        return service.getSalarySum(departmentId);
    }

    @GetMapping("/{id}/salary/max")
    public int getDepartmentSalaryMax(@PathVariable("id") int departmentId) {
        return service.getSalaryMax(departmentId);
    }

    @GetMapping("/{id}/salary/min")
    public int getDepartmentSalaryMin(@PathVariable("id") int departmentId) {
        return service.getSalaryMin(departmentId);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getEmployees() {
        return service.getEmployees();
    }
}

//
//GET http://localhost:8080/department/employees
// — возвращает сотрудников, сгруппированых по отделам в виде Map<Integer,List<Employees>>, где ключ — это номер отдела, а значение — список сотрудников данного отдела.