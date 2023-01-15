package com.rest.api.controllers;

import com.rest.api.entity.Person;
import com.rest.api.services.PersonService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("/api/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonController {

    @Inject
    private PersonService personService;

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        System.out.println(personService.getAllPersons());
        return "Hello from RESTEasy Reactive";
    }

    @POST
    @Path("/")
    public Response createPerson(Person person) {
        try {
            if (person != null && !person.getName().equals("") && !person.getCity().equals("") && !person.getSalary().equals("")) {

               Boolean isCreated = personService.createPerson(person);

               if(isCreated){
                   return Response.status(Response.Status.CREATED)
                           .entity(Map.of("message", "Person Created", "isSuccess", true, "statusCode", 201)).build();
               } else {
                   return Response.status(Response.Status.BAD_REQUEST)
                           .entity(Map.of("message", "Error occur when Person are Created", "isSuccess", false, "statusCode", 400)).build();
               }
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(Map.of("message", "All Fields are required", "isSuccess", false, "statusCode", 400)).build();
            }
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity(Map.of("message", "Internal Server Error", "isSuccess", false, "statusCode", 501)).build();
        }
    }

    @Path("/")
    @GET
    public Response getAllPersons(){
        try {
           List<Person> persons = personService.getAllPersons();

           if(persons!=null && persons.size()>0){
               return Response.status(Response.Status.OK)
                       .entity(Map.of("message", "Person List", "isSuccess", true, "statusCode", 200,"data",persons)).build();
           }
           else{
               return Response.status(Response.Status.NOT_FOUND)
                       .entity(Map.of("message", "Data Not Found", "isSuccess", false, "statusCode", 404,"data",persons)).build();
           }
        }
        catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity(Map.of("message", "Internal Server Error", "isSuccess", false, "statusCode", 501)).build();
        }
    }


    @Path("/{id}")
    @GET
    public Response getPerson(@PathParam("id") String id){
        try {
            Person person = personService.getPerson(id);
            if(person != null && !person.getName().equals("") && !person.getCity().equals("") && !person.getSalary().equals("")){
                return Response.status(Response.Status.OK)
                        .entity(Map.of("message", "Person List", "isSuccess", true, "statusCode", 200,"data",person)).build();
            }
            else{
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(Map.of("message", "Data Not Found", "isSuccess", false, "statusCode", 404,"data",person)).build();
            }
        }
        catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity(Map.of("message", "Internal Server Error", "isSuccess", false, "statusCode", 501)).build();
        }
    }

    @Path("/{id}")
    @DELETE
    public Response deletePerson(@PathParam("id") String id){
        try {
            Boolean isDeleted = personService.deletePerson(id);

            if(isDeleted){
                return Response.status(Response.Status.OK)
                        .entity(Map.of("message", "Person Deleted", "isSuccess", true, "statusCode", 200)).build();
            }
            else{
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(Map.of("message", "Bad Request", "isSuccess", false, "statusCode", 400)).build();
            }
        }
        catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity(Map.of("message", "Internal Server Error", "isSuccess", false, "statusCode", 501)).build();
        }
    }


    @Path("/")
    @PUT
    public Response updatedPerson(Person person){
        try {
            Boolean isUpdated = personService.updatedPerson(person);

            if(isUpdated){
                return Response.status(Response.Status.OK)
                        .entity(Map.of("message", "Person Updated", "isSuccess", true, "statusCode", 203)).build();
            }
            else{
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(Map.of("message", "Bad Request", "isSuccess", false, "statusCode", 400)).build();
            }
        }
        catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity(Map.of("message", "Internal Server Error", "isSuccess", false, "statusCode", 501)).build();
        }
    }



}