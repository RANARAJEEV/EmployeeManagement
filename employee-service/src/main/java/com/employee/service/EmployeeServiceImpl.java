package com.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.exception.DepartmentNotFound;
import com.employee.feignclient.DepartmentClient;
import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService 
{
	@Autowired private EmployeeRepository employeeRepository;
	@Autowired private DepartmentClient departmentClient;
	
	public Employee addEmployee(Employee employee) 
	{
		//Code to save employee
		int did=employee.getDid();
		//Call department service to check existence of department
		Boolean exist=departmentClient.getDepartmentById(did);
		if(!exist)
			throw new DepartmentNotFound("Department with id "+did+" does not exist");
		Employee emp=employeeRepository.save(employee);
		//Code to increase total_employee value by 1 in Department object
		//Call department service to increase total employees by 1
		departmentClient.increaseTotalEmployees(did);
		return emp;
	}

	public Employee getById(int eid) 
	{
		return employeeRepository.findById(eid).orElse(null);
	}

	public List<Employee> getList() 
	{
		return employeeRepository.findAll();
	}

	public boolean isEmployeeExist(int eid) 
	{
		return employeeRepository.existsById(eid);
	}

	public void deleteEmployee(int eid) 
	{
		Employee employee=getById(eid);
		employeeRepository.deleteById(eid);
		//Code to decrease total_employee value by 1 in Department object
		//Call department service to decrease total employees by 1
		departmentClient.decreaseTotalEmployees(employee.getDid());
	}
}