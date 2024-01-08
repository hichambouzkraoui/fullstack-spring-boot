package net.javaguides.springboot.integrationtests;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
@ActiveProfiles("tc")
@Tag("IntegrationTests")
public class EmployeeRepositoryIntTests {
    @Autowired
    public EmployeeRepository employeeRepository;
    @BeforeEach
    public void cleanUp(){
        employeeRepository.deleteAll();
    }

    @Test
    public void EmployeeRepository_Save_ReturnSavedEmployee() {
        employeeRepository.save(new Employee("first1","last1","email@gmail.com"));
        var employees = employeeRepository.findAll();
        assertThat(employees).hasSize(1);
    }
}
