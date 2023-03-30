package com.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository repository;
	
	public List<Employee> getAllEmployees(){
		return repository.findAll();
	}
	
	public Employee addEmployee(Employee employee) {
		return repository.save(employee);
	}
}
