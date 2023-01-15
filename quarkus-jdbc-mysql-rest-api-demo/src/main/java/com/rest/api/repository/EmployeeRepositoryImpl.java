package com.rest.api.repository;

import com.rest.api.models.EmployeeDto;
import io.agroal.api.AgroalDataSource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Inject
    AgroalDataSource defaultDataSource;


    @Override
    public Boolean createEmployee(EmployeeDto employee) {
        try {
            Connection connection = defaultDataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement("insert into employee(name,city,salary) values(?,?,?)");
            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getCity());
            stmt.setString(3, employee.getSalary());

            int i = stmt.executeUpdate();

            if (i > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception exception) {
            exception.getMessage();
        }
        return false;
    }

    @Override
    public Boolean deleteEmployee(Integer employeeId) {
        try {
            Connection connection = defaultDataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement("delete from employee where id=?");
            stmt.setInt(1, employeeId);

            int i = stmt.executeUpdate();

            if (i > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception exception) {
            exception.getMessage();
        }
        return false;
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        EmployeeDto employeeDto = null;
        List<EmployeeDto> employeeDtoList = new ArrayList<EmployeeDto>();;
        try {
            Connection connection = defaultDataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement("select * from employee");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                employeeDto = new EmployeeDto();
                employeeDto.setId(rs.getInt("id"));
                employeeDto.setName(rs.getString("name"));
                employeeDto.setCity(rs.getString("city"));
                employeeDto.setSalary(rs.getString("salary"));
                employeeDtoList.add(employeeDto);
            }

            if(employeeDtoList!=null && employeeDtoList.size()>0){
                return employeeDtoList;
            }
            else{
                return employeeDtoList;
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return employeeDtoList;
    }

    @Override
    public EmployeeDto getEmployee(Integer employeeId) {
        EmployeeDto employeeDto = new EmployeeDto();
        try {
            Connection connection = defaultDataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement("select * from employee where id = ?");
            stmt.setInt(1,employeeId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                employeeDto.setId(rs.getInt("id"));
                employeeDto.setName(rs.getString("name"));
                employeeDto.setCity(rs.getString("city"));
                employeeDto.setSalary(rs.getString("salary"));
            }

            if(employeeDto!=null && !employeeDto.getName().equals("")){
                return employeeDto;
            }
            else{
                return employeeDto;
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return employeeDto;
    }

    @Override
    public Boolean updateEmployee(Integer employeeId, EmployeeDto employee) {
        System.out.println(employeeId+" "+employee);
        try {
            Connection connection = defaultDataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement("update employee set name=?,city=?,salary=? where id = ?");
            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getCity());
            stmt.setString(3, employee.getSalary());
            stmt.setInt(4,employeeId);

            int i = stmt.executeUpdate();
            System.out.println(i);

            if (i > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception exception) {
            exception.getMessage();
        }
        return false;
    }
}
