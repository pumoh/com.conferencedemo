package com.conferencedemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.conferencedemo.model.Employee;
import com.conferencedemo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;
	
	//@Override
	public List<Employee> findAll() {
		return employeeRepo.findAll();
	}

	@Override
	public Employee add(Employee employee) {
		return employeeRepo.insert(employee);
	}

	@Override
	public void delete(String empNo) {
		Optional<Employee> entity = findEmployee(empNo);
		if (entity.isPresent()) { 
			employeeRepo.delete(entity.get());
		}
	}

	private Optional<Employee> findEmployee(String empNo) {
		ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAny().withMatcher("empNo", ExampleMatcher.GenericPropertyMatchers.exact());
		Employee employeMatch = new Employee();
		employeMatch.setEmpNo(empNo);
		Example<Employee> example = Example.of(employeMatch, customExampleMatcher);
		Optional<Employee> entity = employeeRepo.findOne(example);
		return entity;
	}

	@Override
	public Employee get(String empNo) {
		return findEmployee(empNo).get();
	}

	@Override
	public Employee update(Employee employee) {
		Optional<Employee> entity = findEmployee(employee.getEmpNo());
		if (entity.isPresent()) { 
			Employee updatedEmployee = entity.get();
			updatedEmployee.setEmpName(employee.getEmpName());
			updatedEmployee.setPosition(employee.getPosition());
			return employeeRepo.save(updatedEmployee);
		} else {
			return employeeRepo.insert(employee);
		}
	}
}
