package com.example.consumerest.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.consumerest.model.Employee;

@Service
public class EmployeeService {
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
		
	public List<Employee> getAllEmployees() {
		RestTemplate restTemplate = new RestTemplate();
		List<Employee> employeeList = new ArrayList<Employee>();
		Employee[] employees = restTemplate.getForObject("http://localhost:8080/employees", Employee[].class);
		employeeList = Arrays.asList(employees);
		for (Employee emp : employees) {
			log.info(emp.toString());
		}
		return employeeList;

	}
	
	public Employee update(Employee employee) {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Employee> entity = new HttpEntity<Employee>(employee);
		ResponseEntity<Employee> newEmployee = restTemplate.exchange("http://localhost:8080/employee/{empNo}", HttpMethod.PUT, entity , Employee.class, employee.getEmpNo());
		log.info(newEmployee.getBody().toString());
		return newEmployee.getBody();

	}

	public void delete(String empNo) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete("http://localhost:8080/employee/{empNo}", empNo);
	}

}
