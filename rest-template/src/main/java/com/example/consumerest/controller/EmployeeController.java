package com.example.consumerest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.consumerest.model.Employee;
import com.example.consumerest.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping(value = "employees",method = RequestMethod.GET)   // or use @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

	@RequestMapping(value = "/employee/{empNo}", method = RequestMethod.PUT, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE
	}) 	
	public Employee updateEmployees(@PathVariable("empNo") String empNo, @RequestBody Employee employee) {
		employee.setEmpNo(empNo);
		return employeeService.update(employee);
	}

	@RequestMapping(value = "/employee/{empNo}", method = RequestMethod.DELETE)
	public void deleteEmployee(@PathVariable("empNo") String empNo) {
		employeeService.delete(empNo);
	}
}
