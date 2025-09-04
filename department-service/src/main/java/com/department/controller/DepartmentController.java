package com.department.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.department.model.Department;
import com.department.service.DepartmentService;

@RestController
public class DepartmentController 
{
	@Autowired private DepartmentService departmentService;
	
	@PostMapping("/add")
	public ResponseEntity<Department> createDepartment(@RequestBody Department department)
	{
		Department createdDepartment=departmentService.addDepartment(department);
		return new ResponseEntity<>(createdDepartment,HttpStatus.CREATED);
	}
	@GetMapping("/list")
	public ResponseEntity<List<Department>> getDepartmentList()
	{
		List<Department> departments=departmentService.getList();
		if(!departments.isEmpty())
			return ResponseEntity.ok(departments);
		return ResponseEntity.noContent().build();
	}
	@GetMapping("/{did}")
	public ResponseEntity<Department> getDepartment(@PathVariable int did)
	{
		Department department=departmentService.getDepartment(did);
		return ResponseEntity.ok(department);
	}
	@GetMapping("/exist/{did}")
	public ResponseEntity<Boolean> getDepartmentExistence(@PathVariable int did)
	{
		return ResponseEntity.ok(departmentService.isExist(did));
	}
	@GetMapping("/increase-total-employees/{did}")
	public void increaseTotalEmployees(@PathVariable int did)
	{
		departmentService.updateTotalEmployees(did,1);
	}
	@GetMapping("/decrease-total-employees/{did}")
	public void decreaseTotalEmployees(@PathVariable int did)
	{
		departmentService.updateTotalEmployees(did,-1);
	}
}
