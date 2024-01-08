package net.javaguides.springboot;

import net.javaguides.springboot.controller.EmployeeController;
import static org.assertj.core.api.Assertions.assertThat;

import net.javaguides.springboot.service.EmployeeService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Tag("UnitTests")
class SpringbootBackendApplicationTests {
	@Autowired
	private EmployeeController controller;
	@Autowired
	private EmployeeService service;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
		assertThat(service).isNotNull();
	}

}
