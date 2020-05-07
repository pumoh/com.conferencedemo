package com.conferencedemo.service;

import java.util.List;

import com.conferencedemo.model.Employee;

public interface EmployeeService {

	public List<Employee> findAll();
	
	public Employee get(String empNo);

	public Employee add(Employee employee);

	public Employee update(Employee employee);

	void delete(String empNo);
}
