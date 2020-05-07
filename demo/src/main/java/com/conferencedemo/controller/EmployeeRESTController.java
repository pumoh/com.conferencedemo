package com.conferencedemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.conferencedemo.model.Employee;
import com.conferencedemo.service.EmployeeService;

@RestController
public class EmployeeRESTController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/")
	public String welcome() {
		return "Welcome to RestTemplate Demo.";
	}

	@RequestMapping(value = "/employees", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Employee> listEmployees() {
		return employeeService.findAll();
	}

	@RequestMapping(value = "/employee", //
			method = RequestMethod.POST, //
			consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeService.add(employee);
	}

	@RequestMapping(value = "/employee/{empNo}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public Employee getEmployee(@PathVariable("empNo") String empNo) {
		return employeeService.get(empNo);
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