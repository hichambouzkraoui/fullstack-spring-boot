package net.javaguides.springboot.integrationtests;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
@ActiveProfiles("tc")
@Tag("IntegrationTests")
public class EmployeeRepositoryIntTests {

//    @Container
//    public static MySQLContainer<?>  mysql = new MySQLContainer("mysql:5.5")
//                                             .withDatabaseName("employee_management_system")
//                                              .withUsername("root")
//                                              .withPassword("root");
//    @DynamicPropertySource
//    static void configureTestcontainersProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url",mysql::getJdbcUrl);
//        registry.add("spring.datasource.username",mysql::getUsername);
//        registry.add("spring.datasource.password",mysql::getPassword);
//    }
    @Autowired
    public EmployeeRepository employeeRepository;
    @BeforeEach
    public void cleanUp(){
        employeeRepository.deleteAll();
    }

    @Test
    public void EmployeeRepository_Save_ReturnSavedEmployee() {
        var employee = employeeRepository.save(new Employee("first1","last1","email@gmail.com"));
        var employees = employeeRepository.findAll();
        assertThat(employees).hasSize(1);
    }
}
