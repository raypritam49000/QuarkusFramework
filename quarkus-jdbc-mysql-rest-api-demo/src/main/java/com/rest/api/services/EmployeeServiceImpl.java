package com.rest.api.services;

import com.rest.api.models.EmployeeDto;
import com.rest.api.repository.EmployeeRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.SQLException;
import java.util.List;

@Singleton
public class EmployeeServiceImpl implements EmployeeService {

    @Inject
    private EmployeeRepository employeeRepository;

    @Override
    public Boolean createEmployee(EmployeeDto employee) throws SQLException {
        return this.employeeRepository.createEmployee(employee);
    }

    @Override
    public Boolean deleteEmployee(Integer employeeId) {
        return this.employeeRepository.deleteEmployee(employeeId);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        return this.employeeRepository.getAllEmployee();
    }

    @Override
    public EmployeeDto getEmployee(Integer employeeId) {
        return this.employeeRepository.getEmployee(employeeId);
    }

    @Override
    public Boolean updateEmployee(Integer employeeId, EmployeeDto employee) {
        return this.employeeRepository.updateEmployee(employeeId,employee);
    }
}
