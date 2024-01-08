package net.javaguides.springboot.integrationtests;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@ActiveProfiles("tc")
@Tag("IntegrationTests")
public class EmployeeControllerIntTests {
    @Autowired
    private MockMvc mockMvc;
    private @Autowired
    EmployeeRepository employeeRepository;
    @BeforeEach
    public void cleanUp(){
        employeeRepository.deleteAll();
    }

    @Test
    public void test_wrong_uri() throws Exception  {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void test_get_employee() throws Exception  {
        Employee employee = employeeRepository.save(new Employee("first1","last1", "email1@gmail.com"));
        this.mockMvc.perform(get("/api/v1/employees/"+employee.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("first1"))
                .andExpect(jsonPath("$.lastName").value("last1"))
                .andExpect(jsonPath("$.emailId").value("email1@gmail.com"));
    }
}
