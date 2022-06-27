package com.rest.api.controllers;

import com.rest.api.models.Person;
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
        return "Hello from RESTEasy Reactive";
    }

    @POST
    @Path("/")
    public Response createPerson(Person person){
       try {
           if(person!=null && !person.getName().equals("") && !person.getCity().equals("") && !person.getSalary().equals("")){
               Boolean isCreated = personService.addPerson(person);

               if(isCreated){
                   return Response.ok(Map.of("message", "Person Created","statusCode",201,"isSuccess",true)).build();
               }
               else{
                   return Response.ok(Map.of("message", "Error occur when create Person","statusCode",400,"isSuccess",false)).build();
               }

           }
           else {
               return Response.ok(Map.of("message", "All Parameter are required","statusCode",400,"isSuccess",false)).build();
           }
       }
       catch (Exception ex){
           return Response.ok(Map.of("message", ex.getMessage(),"statusCode",501,"isSuccess",false)).build();
       }
    }


    @GET
    @Path("/")
    public Response getAllPersons(){
        try {
             List<Person> persons = personService.getAllPersons();

             if(persons!=null && persons.size()>0){
                 return Response.ok(Map.of("message", "Person List","statusCode",200,"isSuccess",true,"data",persons)).build();
             }
             else{
                 return Response.ok(Map.of("message", "Person Data Not Found","statusCode",404,"isSuccess",false)).build();
             }
        }
        catch (Exception ex){
            return Response.ok(Map.of("message", ex.getMessage(),"statusCode",501,"isSuccess",false)).build();
        }
    }



    @GET
    @Path("/{id}")
    public Response getPerson(@PathParam("id") String id){
        try {
            Person person = personService.getPerson(id);

            if(person!=null){
                return Response.ok(Map.of("message", "Person List","statusCode",200,"isSuccess",true,"data",person)).build();
            }
            else{
                return Response.ok(Map.of("message", "Person Data Not Found","statusCode",404,"isSuccess",false)).build();
            }
        }
        catch (Exception ex){
            return Response.ok(Map.of("message", ex.getMessage(),"statusCode",501,"isSuccess",false)).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") String id){
        try {
            Boolean isDeleted = personService.deletePerson(id);

            if(isDeleted){
                return Response.ok(Map.of("message", "Person Deleted","statusCode",204,"isSuccess",true)).build();
            }
            else{
                return Response.ok(Map.of("message", "Bad Request","statusCode",400,"isSuccess",false)).build();
            }
        }
        catch (Exception ex){
            return Response.ok(Map.of("message", ex.getMessage(),"statusCode",501,"isSuccess",false)).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updatePerson(@PathParam("id") String id,Person person){
        try {
            Boolean isUpdated = personService.updatePerson(id, person);

            if(isUpdated){
                return Response.ok(Map.of("message", "Person Updated","statusCode",203,"isSuccess",true)).build();
            }
            else{
                return Response.ok(Map.of("message", "Bad Request","statusCode",400,"isSuccess",false)).build();
            }
        }
        catch (Exception ex){
            return Response.ok(Map.of("message", ex.getMessage(),"statusCode",501,"isSuccess",false)).build();
        }
    }
}