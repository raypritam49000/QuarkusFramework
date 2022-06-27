package com.rest.api.controllers;

import com.rest.api.models.EmployeeDto;
import com.rest.api.services.EmployeeService;
import io.agroal.api.AgroalDataSource;
import org.modelmapper.ModelMapper;

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
    AgroalDataSource defaultDataSource;

    @Inject
    private ModelMapper modelMapper;

    @Inject
    private EmployeeService employeeService;

    @GET
    @Path("/hello")
    public String hello() {
        System.out.println(modelMapper);
        System.out.println("EmployeeController.hello" + defaultDataSource);
        return "Hello from RESTEasy Reactive";
    }

    @GET
    @Path("/")
    public Response getAllEmployees() {
        try {
            List<EmployeeDto> employees = this.employeeService.getAllEmployee();
            System.out.println(employees);

            if (employees != null && employees.size() > 0) {
                return Response.ok(Map.of("message", "Employee List", "statusCode", 200, "isSuccess", true, "data", employees)).build();
            } else {
                return Response.ok(Map.of("message", "Data Not Found", "statusCode", 404, "isSuccess", false, "data", employees)).build();
            }
        } catch (Exception ex) {
            return Response.ok(Map.of("message", ex.getMessage(), "statusCode", 501, "isSuccess", false)).build();
        }
    }

    @POST
    @Path("/")
    public Response createEmployee(EmployeeDto employee) {
        try {
            Boolean isSaved = this.employeeService.createEmployee(employee);

            if (isSaved) {
                return Response.ok(Map.of("message", "Employee Created", "statusCode", 201, "isSuccess", true)).build();
            } else {
                return Response.ok(Map.of("message", "Bad Request", "statusCode", 400, "isSuccess", false)).build();
            }

        } catch (Exception ex) {
            return Response.ok(Map.of("message", ex.getMessage(), "statusCode", 501, "isSuccess", false)).build();
        }

    }

    @GET
    @Path("/{id}")
    public Response getEmployee(@PathParam("id") Integer id) {
        try {
            EmployeeDto employee = this.employeeService.getEmployee(id);

            if (employee != null && !employee.getName().equals("")) {
                return Response.ok(Map.of("message", "Employee List", "statusCode", 200, "isSuccess", true, "data", employee)).build();
            } else {
                return Response.ok(Map.of("message", "Data Not Found", "statusCode", 404, "isSuccess", false, "data", employee)).build();
            }
        } catch (Exception ex) {
            return Response.ok(Map.of("message", "Internal Server Error", "statusCode", 501, "isSuccess", false)).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEmployee(@PathParam("id") Integer id) {
        try {
            Boolean isDeleted = this.employeeService.deleteEmployee(id);

            if (isDeleted) {
                return Response.ok(Map.of("message", "Employee Deleted", "statusCode", 203, "isSuccess", true)).build();
            } else {
                return Response.ok(Map.of("message", "Bad Request", "statusCode", 400, "isSuccess", false)).build();
            }
        } catch (Exception ex) {
            return Response.ok(Map.of("message", "Internal Server Error", "statusCode", 501, "isSuccess", false)).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updatedEmployee(@PathParam("id") Integer id,EmployeeDto employee) {
        try {
            Boolean isUpdated = employeeService.updateEmployee(id,employee);

            if (isUpdated) {
                return Response.ok(Map.of("message", "Employee Updated", "statusCode", 204, "isSuccess", true)).build();
            } else {
                return Response.ok(Map.of("message", "Bad Request", "statusCode", 400, "isSuccess", false)).build();
            }
        } catch (Exception ex) {
            return Response.ok(Map.of("message", "Internal Server Error", "statusCode", 501, "isSuccess", false)).build();
        }
    }




}