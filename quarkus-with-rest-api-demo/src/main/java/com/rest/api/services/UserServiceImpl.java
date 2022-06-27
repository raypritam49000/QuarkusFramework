package com.rest.api.services;

import com.rest.api.model.Employee;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class UserServiceImpl implements UserService {

    public static List<Employee> employees = null;

    static {
        employees =  List.of(
                new Employee(1, "Pritam Ray", "Ropar", "45000"),
                new Employee(2, "Omi Verma", "Rail", "34000"),
                new Employee(3, "Amit Kumar", "Prem Nagar", "24000"),
                new Employee(4, "Ajit Kumar", "Asron", "34000")
        );
    }

    @Override
    public List<Employee> createEmployee(Employee employee) {
        employees =  List.of(
                new Employee(1, "Pritam Ray", "Ropar", "45000"),
                new Employee(2, "Omi Verma", "Rail", "34000"),
                new Employee(3, "Amit Kumar", "Prem Nagar", "24000"),
                new Employee(4, "Ajit Kumar", "Asron", "34000"),
                employee
        );
        return employees;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employees;
    }

    @Override
    public Employee getEmployee(Integer id) {
        Employee employee = employees.stream().filter(emp -> { return emp.getId()==id;}).findFirst().get();
        return employee;
    }

    @Override
    public Employee updateEmployee(Integer id, Employee employee) {
        Employee existEmployee = employees.stream().filter(emp -> { return emp.getId()==id;}).findFirst().get();
        if(existEmployee!=null){
            existEmployee.setName(employee.getName());
            existEmployee.setCity(employee.getCity());
            existEmployee.setSalary(employee.getSalary());
        }
        return existEmployee;
    }
}
