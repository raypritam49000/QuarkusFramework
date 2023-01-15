package com.rest.api.services;

import com.rest.api.models.EmployeeDto;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService {
    public Boolean createEmployee(EmployeeDto employee) throws SQLException;
    public Boolean deleteEmployee(Integer employeeId);
    public List<EmployeeDto> getAllEmployee();
    public EmployeeDto getEmployee(Integer employeeId);
    public Boolean updateEmployee(Integer employeeId,EmployeeDto employee);
}
