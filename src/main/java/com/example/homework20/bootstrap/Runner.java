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
        service.add("Иван", "Иванович", 1, 5000);
        service.add("Василий", "Петрович", 4, 2000);
        service.add("Михаил", "Васильевич", 3, 7000);
        service.add("Клубника", "Николаевна", 2, 7000);
        service.add("Вишня", "Владимировна", 3, 4000);
        service.add("Курага", "Петровна", 2, 8000);
        service.add("Чернослив", "Михайлович", 1, 5000);
        service.add("Миндаль", "Иванович", 5, 3000);
        service.add("Фундук", "Петрович", 2, 7000);
        service.add("Вальдемар", "Иванович", 1, 300000);
    }
}
