package com.rest.api.services;

import com.rest.api.model.Employee;

import java.util.List;

public interface UserService {
    public List<Employee> createEmployee(Employee employee);
    public List<Employee> getAllEmployees();
    public Employee getEmployee(Integer id);
    public Employee updateEmployee(Integer id,Employee employee);
}
