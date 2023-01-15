package com.rest.api.controllers;

import com.rest.api.model.Employee;
import com.rest.api.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("/api/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeController {

    @Inject
    private UserService userService;

    @GET
    public Response getAllEmployees() {
       try {
           List<Employee> employees = this.userService.getAllEmployees();
          if(employees!=null && employees.size()>0){
              return Response.ok(Map.of("message","Employee List","isSuccess",true,"data",employees)).build();
          }
          else{
              return Response.ok(Map.of("message","Employee Data Not Found","isSuccess",false,"data",employees)).build();
          }
       }
       catch (Exception exception){
           return Response.ok(Map.of("message", exception.getMessage(),"isSuccess",false)).build();
       }
    }

    @GET
    @Path("/{id}")
    public Response getEmployee(@PathParam("id") Integer id) {
        try {
            Employee employee = this.userService.getEmployee(id);
            if(employee!=null && !employee.getName().equals("")){
                return Response.ok(Map.of("message","Employee List","isSuccess",true,"data",employee)).build();
            }
            else{
                return Response.ok(Map.of("message","Employee Data Not Found","isSuccess",false,"data",employee)).build();
            }
        }
        catch (Exception exception){
            return Response.ok(Map.of("message", exception.getMessage(),"isSuccess",false)).build();
        }
    }

    @POST
    @Path("/")
    public Response createEmployee(Employee employee) {
        System.out.println(employee);
        try {
            List<Employee> createdEmployee = this.userService.createEmployee(employee);
            if(createdEmployee!=null && createdEmployee.size()>0){
                return Response.ok(Map.of("message","Employee Created","isSuccess",true,"data",createdEmployee)).build();
            }
            else{
                return Response.ok(Map.of("message","All Parameter are required !!! ","isSuccess",false)).build();
            }
        }
        catch (Exception exception){
            return Response.ok(Map.of("message", exception.getMessage(),"isSuccess",false)).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updatedEmployee(@PathParam("id") Integer id,Employee employee) {
        try {
            if(id!=null && employee!=null && !employee.getName().equals("")){
                Employee updatedEmployee = this.userService.updateEmployee(id, employee);
                return Response.ok(Map.of("message","Employee Updated","isSuccess",true,"data",updatedEmployee)).build();
            }
            else{
                return Response.ok(Map.of("message","All Parameter are required !!! ","isSuccess",false)).build();
            }
        }
        catch (Exception exception){
            return Response.ok(Map.of("message", exception.getMessage(),"isSuccess",false)).build();
        }
    }
}