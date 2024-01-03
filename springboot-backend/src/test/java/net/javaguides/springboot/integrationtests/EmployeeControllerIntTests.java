package net.javaguides.springboot.integrationtests;

import net.javaguides.springboot.model.Employee;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerIntTests {
    @Autowired
    private MockMvc mockMvc;
    private static List<Employee> employees;
    private static Employee emp0, emp1, emp2;
    @BeforeAll
    public static void setUp() {
        emp0 = new Employee("first0","last0", "email0@gmail.com");
        emp1 = new Employee("first1","last1", "email1@gmail.com");
        emp2 = new Employee("first2","last2", "email2@gmail.com");
        employees = Arrays.asList( emp0,emp1,emp2);
    }

    @Test
    public void test_wrong_uri() throws Exception  {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void test_get_employee() throws Exception  {
        this.mockMvc.perform(get("/api/v1/employees/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("hicham"))
                .andExpect(jsonPath("$.lastName").value("bouzkraoui"))
                .andExpect(jsonPath("$.emailId").value("test@gmail.com"));
    }
}
