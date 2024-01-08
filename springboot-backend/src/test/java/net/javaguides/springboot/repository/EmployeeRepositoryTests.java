package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Tag("UnitTests")
public class EmployeeRepositoryTests {
    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void cleanUp(){
        employeeRepository.deleteAll();
    }

    @Test
    public void EmployeeRepository_Save_ReturnSavedEmployee() {
        Employee employee = new Employee("first1", "last1", "email1@gmail.com");
        Employee savedEmployee = employeeRepository.save(employee);
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);

    }
    @Test
    public void EmployeeRepository_SaveAll_ReturnSavedEmployees() {
        Employee employee1 = new Employee("first1", "last1", "email1@gmail.com");
        Employee employee2 = new Employee("first2", "last2", "email2@gmail.com");
        List<Employee> savedEmployees = employeeRepository.saveAll(Arrays.asList(employee1, employee2));
        assertThat(savedEmployees).hasSize(2);
        assertThat(savedEmployees.get(0).getId()).isGreaterThan(0);
        assertThat(savedEmployees.get(1).getId()).isGreaterThan(0);

    }

    @Test
    public void EmployeeRepository_GetAll_ReturnAllEmployees() {
        List<Employee> allEmployees = employeeRepository.findAll();
        assertThat(allEmployees).hasSize(0);
        Employee employee1 = new Employee("first1", "last1", "email1@gmail.com");
        Employee employee2 = new Employee("first2", "last2", "email2@gmail.com");
        employeeRepository.saveAll(Arrays.asList(employee1, employee2));
        allEmployees = employeeRepository.findAll();
        assertThat(allEmployees).hasSize(2);
        assertThat(allEmployees.get(0).getId()).isGreaterThan(0);
        assertThat(allEmployees.get(1).getId()).isGreaterThan(0);


    }

    @Test
    public  void EmployeeRepository_DeleteAll() {
        Employee employee1 = new Employee("first1", "last1", "email1@gmail.com");
        Employee employee2 = new Employee("first2", "last2", "email2@gmail.com");
        employeeRepository.saveAll(Arrays.asList(employee1, employee2));
        assertThat(employeeRepository.findAll()).hasSize(2);
        employeeRepository.deleteAll();
        assertThat(employeeRepository.findAll()).hasSize(0);
    public void EmployeeRepository_DeleteById(){
        assertThat(employeeRepository.findAll()).hasSize(0);
        Employee employee = employeeRepository.save(new Employee("first1", "last1", "email1@gmail.com"));
        assertThat(employeeRepository.findAll()).hasSize(1);
        employeeRepository.deleteById(employee.getId());
        assertThat(employeeRepository.findAll()).hasSize(0);

    }

}
