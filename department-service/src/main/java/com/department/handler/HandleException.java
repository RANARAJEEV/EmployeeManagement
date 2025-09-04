package com.department.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.department.exception.DepartmentNotFound;

@RestControllerAdvice
public class HandleException 
{
	@ExceptionHandler(DepartmentNotFound.class)
	public ResponseEntity<String> handleDepartmentNotFound(DepartmentNotFound ex)
	{
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
}
