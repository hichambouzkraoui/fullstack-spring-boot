package net.javaguides.springboot.controller;

import java.util.List;
import java.util.Map;

import net.javaguides.springboot.dto.EmployeeDto;
import net.javaguides.springboot.dto.EmployeeResponse;
import net.javaguides.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.model.Employee;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	// get all employees
	@GetMapping("/employees")
	public List<EmployeeDto> getAllEmployees(){
		return employeeService.getAllEmployees();
	}		
	
	// create employee rest api
	@PostMapping("/employees")
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
		return ResponseEntity.status(201).body(employeeService.createEmployee(employeeDto));
	}
	
	// get employee by id rest api
	@GetMapping("/employees/{id}")
	public ResponseEntity<EmployeeDto>  getEmployeeById(@PathVariable Long id) {
		return ResponseEntity.ok(employeeService.getEmployeeById(id));
	}
	
	// update employee rest api
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<EmployeeDto>  updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDetails){
		return ResponseEntity.ok(employeeService.updateEmployee(id, employeeDetails));
	}
	
	// delete employee rest api
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<EmployeeDto>  deleteEmployee(@PathVariable Long id){
		return ResponseEntity.ok(employeeService.deleteEmployee(id));
	}
	
	
}
