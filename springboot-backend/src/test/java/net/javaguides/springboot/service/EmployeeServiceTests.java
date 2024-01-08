package net.javaguides.springboot.service;

import net.javaguides.springboot.dto.EmployeeDto;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("UnitTests")
public class EmployeeServiceTests {
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;
    private static List<Employee> employees;
    private static Employee emp0, emp1, emp2;
    @BeforeAll
    public static void setUp() {
        emp0 = new Employee("first0","last0", "email0@gmail.com");
        emp1 = new Employee("first1","last1", "email1@gmail.com");
        emp2 = new Employee("first2","last2", "email2@gmail.com");
        employees = Arrays.asList( emp0,emp1,emp2);
    }
    @BeforeEach
    public void cleanUp(){
        employeeRepository.deleteAll();
    }
    @Test
    public void getAllEmployees_returnsAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(employees);
        List<EmployeeDto> employeesDto = employeeService.getAllEmployees();
        assertThat(employeesDto).hasSize(3);

    }

    @Test
    public void get_specificEmployee() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(emp1));
        EmployeeDto employeeDto = employeeService.getEmployeeById(1L);
        assertThat(employeeDto).isNotNull();
        assertAll(
                () -> assertThat(employeeDto).hasFieldOrPropertyWithValue("firstName",emp1.getFirstName()),
                () -> assertThat(employeeDto).hasFieldOrPropertyWithValue("lastName",emp1.getLastName()),
                () -> assertThat(employeeDto).hasFieldOrPropertyWithValue("emailId",emp1.getEmailId())
        );

    }
}
