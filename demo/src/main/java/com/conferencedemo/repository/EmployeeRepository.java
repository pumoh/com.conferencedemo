package com.conferencedemo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.conferencedemo.model.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

	@Override
	List<Employee> findAll();
	
	@Override
	Employee insert(Employee employee);
	

	Optional<Employee> findOne(Example<Employee> example);
	
	Employee save(Employee employee);
}
