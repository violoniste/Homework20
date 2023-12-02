package com.example.homework20.bootstrap;

import com.example.homework20.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private final EmployeeService service;

    public Runner(EmployeeService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {
        service.populate();
    }
}
