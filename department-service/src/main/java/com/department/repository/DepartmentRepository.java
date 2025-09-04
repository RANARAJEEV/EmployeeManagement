package com.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.department.model.Department;

public interface DepartmentRepository extends JpaRepository<Department,Integer> 
{
	@Transactional
	@Modifying	
	@Query("update Department set totalEmployee=totalEmployee+:arg1 where did=:arg2")
	void increaseByOne(@Param("arg2") int did,@Param("arg1") int n);
}
