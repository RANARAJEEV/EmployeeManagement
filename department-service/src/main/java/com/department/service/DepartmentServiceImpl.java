package com.department.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.department.exception.DepartmentNotFound;
import com.department.model.Department;
import com.department.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService 
{
	@Autowired private DepartmentRepository departmentRepository;

	public Department addDepartment(Department department) 
	{
		return departmentRepository.save(department);
	}

	public List<Department> getList() 
	{
		return departmentRepository.findAll();
	}

	public Department getDepartment(int did) 
	{
		Department department=departmentRepository.findById(did).orElse(null);
		if(department==null)
			throw new DepartmentNotFound("Department with id "+did+" does not exist");
		return department;
	}

	public boolean isExist(int did) 
	{
		return departmentRepository.existsById(did);
	}

	public void updateTotalEmployees(int did,int n) 
	{
		departmentRepository.increaseByOne(did,n);
	}
}
