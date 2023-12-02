package com.example.homework20.service;

import com.example.homework20.exceptions.BadRequestException;
import com.example.homework20.exceptions.EmployeeAlreadyAddedException;
import com.example.homework20.exceptions.EmployeeNotFoundException;
import com.example.homework20.exceptions.EmployeeStorageIsFullException;
import com.example.homework20.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class EmployeeServiceTest {

    private EmployeeService out;

    @BeforeEach
    void setUp() {
        out = new EmployeeServiceImpl();
    }

    @Test
    void shouldAddCorrectlyEmployee() {
        out.add("Иван", "Иванович", 1, 5000);
    }

    @Test
    void shouldAddCorrectlyDifferentEmployees() {
        out.add("Иван", "Иванович", 1, 5000);
        out.add("Василий", "Петрович", 1, 5000);
    }

    @Test
    void shouldThrowEmployeeAlreadyAddedException() {
        out.add("Иван", "Иванович", 1, 5000);
        Assertions.assertThrows(EmployeeAlreadyAddedException.class, () -> out.add("Иван", "Иванович", 1, 5000));
    }

    @Test
    void shouldEmployeeStorageIsFullException() {
        out.add("Иван", "Иванович", 1, 5000);
        out.add("Василий", "Петрович", 4, 2000);
        out.add("Михаил", "Васильевич", 3, 7000);
        out.add("Клубника", "Николаевна", 2, 7000);
        out.add("Вишня", "Владимировна", 3, 4000);
        out.add("Курага", "Петровна", 2, 8000);
        out.add("Чернослив", "Михайлович", 1, 5000);
        out.add("Миндаль", "Иванович", 5, 3000);
        out.add("Фундук", "Петрович", 2, 7000);
        out.add("Вальдемар", "Иванович", 1, 300000);
        out.add("ИванА", "Иванович", 1, 5000);
        out.add("ВасилийА", "Петрович", 4, 2000);
        out.add("МихаилА", "Васильевич", 3, 7000);
        out.add("КлубникаА", "Николаевна", 2, 7000);
        out.add("ВишняА", "Владимировна", 3, 4000);
        Assertions.assertThrows(EmployeeStorageIsFullException.class, () -> out.add("Тест", "Тест", 1, 5000));
    }

    @Test
    void shouldThrowBadRequestException() {
        Assertions.assertThrows(BadRequestException.class, () -> out.add("1", "2", 1, 5000));
    }

    @Test
    void shouldThrowEmployeeNotFoundExceptionOnRemove() {
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> out.remove("Иван", "Иванович"));
    }

    @Test
    void shouldRemoveCorrectly() {
        out.add("Иван", "Иванович", 1, 5000);
        out.remove("Иван", "Иванович");
    }

    @Test
    void shouldThrowEmployeeNotFoundExceptionOnFind() {
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> out.find("Иван", "Иванович"));
    }

    @Test
    void shouldFindCorrectly() {
        out.add("Иван", "Иванович", 1, 5000);
        Employee expected = new Employee("Иван", "Иванович", 1, 5000);
        Employee result = out.find("Иван", "Иванович");
        Assertions.assertEquals(expected, result);
    }

    @Test
    void list() {
        out.add("Иван", "Иванович", 1, 5000);
        out.add("Василий", "Петрович", 4, 2000);
        List<Employee> expected = List.of(new Employee("Василий", "Петрович", 4, 2000), new Employee("Иван", "Иванович", 1, 5000));
        List<Employee> result = out.list();
        Assertions.assertEquals(expected.size(), result.size());
        Assertions.assertEquals(expected.get(0), result.get(0));
        Assertions.assertEquals(expected.get(1), result.get(1));
    }
}