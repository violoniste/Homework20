package com.example.homework20.service;

import com.example.homework20.exceptions.EmployeeNotFoundException;
import com.example.homework20.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    private static final List<Employee> list = List.of(new Employee("Иван", "Иванович", 1, 5000), new Employee("Василий", "Петрович", 1, 2000), new Employee("Михаил", "Васильевич", 2, 7000));

    @Mock
    private EmployeeService serviceMock;

    private DepartmentService out;

    @BeforeEach
    void setUp() {
        out = new DepartmentServiceImpl(serviceMock);
    }

    @Test
    void getEmployees() {
        when(serviceMock.list()).thenReturn(list);

        List<Employee> expected = List.of(new Employee("Иван", "Иванович", 1, 5000), new Employee("Василий", "Петрович", 1, 2000));
        List<Employee> result = out.getEmployees(1);
        Assertions.assertEquals(expected.size(), result.size());
        Assertions.assertEquals(expected.get(0), result.get(0));
        Assertions.assertEquals(expected.get(1), result.get(1));

        verify(serviceMock, only()).list();
    }

    @Test
    void shouldReturnSumSalaryCorrectly() {
        when(serviceMock.list()).thenReturn(list);

        int expected = 7000;
        int result = out.getSalarySum(1);
        assertEquals(expected, result);

        verify(serviceMock, only()).list();
    }

    @Test
    void shouldThrowEmployeeNotFoundExceptionOnSalaryMin() {
        when(serviceMock.list()).thenReturn(list);

        assertThrows(EmployeeNotFoundException.class, () -> out.getSalaryMin(3));
    }

    @Test
    void shouldReturnMinSalaryCorrectly() {
        when(serviceMock.list()).thenReturn(list);

        int expected = 2000;
        int result = out.getSalaryMin(1);
        assertEquals(expected, result);

        verify(serviceMock, only()).list();
    }

    @Test
    void shouldThrowEmployeeNotFoundExceptionOnSalaryMax() {
        when(serviceMock.list()).thenReturn(list);

        assertThrows(EmployeeNotFoundException.class, () -> out.getSalaryMax(3));
    }

    @Test
    void shouldReturnMaxSalaryCorrectly() {
        when(serviceMock.list()).thenReturn(list);

        int expected = 5000;
        int result = out.getSalaryMax(1);
        assertEquals(expected, result);

        verify(serviceMock, only()).list();
    }

    @Test
    void testGetEmployees() {
        when(serviceMock.list()).thenReturn(list);

        Map<Integer, List<Employee>> result = out.getEmployees();

        List<Employee> res1 = result.get(1);
        List<Employee> exp1 = List.of(new Employee("Иван", "Иванович", 1, 5000), new Employee("Василий", "Петрович", 1, 2000));
        Assertions.assertEquals(exp1.size(), res1.size());
        Assertions.assertEquals(exp1.get(0), res1.get(0));
        Assertions.assertEquals(exp1.get(1), res1.get(1));

        List<Employee> res2 = result.get(2);
        List<Employee> exp2 = List.of(new Employee("Михаил", "Васильевич", 2, 7000));
        Assertions.assertEquals(exp2.size(), res2.size());
        Assertions.assertEquals(exp2.get(0), res2.get(0));

        verify(serviceMock, only()).list();

        // Может есть какой-то другой способ?
    }
}