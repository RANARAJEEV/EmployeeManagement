package com.employee.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "department-service", url = "http://localhost:8080/department-api") // Replace with Department Service host
public interface DepartmentClient 
{
    @GetMapping("/exist/{did}")
    Boolean getDepartmentById(@PathVariable int did);
    
    @GetMapping("/increase-total-employees/{did}")
    void increaseTotalEmployees(@PathVariable int did);
    
    @GetMapping("/decrease-total-employees/{did}")
    void decreaseTotalEmployees(@PathVariable int did);
}