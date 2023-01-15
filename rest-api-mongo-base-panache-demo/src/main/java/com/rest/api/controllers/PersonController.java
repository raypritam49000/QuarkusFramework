package com.rest.api.controllers;

import com.rest.api.entity.Person;
import org.bson.types.ObjectId;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("/api/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonController {

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
           Boolean createPerson =  Person.savePerson(person);
            System.out.println(Person.getAllPersons());
           if(createPerson){
               return Response.ok(Map.of("message","Person Created","statusCode",201,"isSuccess",true)).build();
           }
           else{
               return Response.ok(Map.of("message","Bad Request","statusCode",400,"isSuccess",false)).build();
           }
        }
        catch(Exception ex){
            return Response.ok(Map.of("message",ex.getMessage(),"statusCode",501,"isSuccess",false)).build();
        }
    }

    @GET
    @Path("/")
    public Response getAllPersons(){
        try {
             List<Person> allPersons =  Person.getAllPersons();

            if(allPersons!=null && allPersons.size()>0){
                return Response.ok(Map.of("message","Person List","statusCode",200,"isSuccess",true,"data",allPersons)).build();
            }
            else{
                return Response.ok(Map.of("message","Data Not Found","statusCode",400,"isSuccess",false)).build();
            }
        }
        catch(Exception ex){
            return Response.ok(Map.of("message",ex.getMessage(),"statusCode",501,"isSuccess",false)).build();
        }
    }


    @GET
    @Path("/{id}")
    public Response getPerson(@PathParam("id") String id){
        try {
            Person person =  Person.getPerson(new ObjectId(id));

            if(person!=null && !person.name.equals("") && !person.city.equals("") && !person.salary.equals("")){
                return Response.ok(Map.of("message","Person List","statusCode",200,"isSuccess",true,"data",person)).build();
            }
            else{
                return Response.ok(Map.of("message","Data Not Found","statusCode",400,"isSuccess",false)).build();
            }
        }
        catch(Exception ex){
            return Response.ok(Map.of("message",ex.getMessage(),"statusCode",501,"isSuccess",false)).build();
        }
    }



    @DELETE
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") String id){
        try {
            Boolean isDeleted =  Person.deletePerson(new ObjectId(id));
            if(isDeleted){
                return Response.ok(Map.of("message","Person Deleted","statusCode",203,"isSuccess",true)).build();
            }
            else{
                return Response.ok(Map.of("message","Bad Request","statusCode",400,"isSuccess",false)).build();
            }
        }
        catch(Exception ex){
            return Response.ok(Map.of("message",ex.getMessage(),"statusCode",501,"isSuccess",false)).build();
        }
    }

    @PUT
    @Path("/")
    public Response updatePerson(Person person){
        try {
            Boolean isUpdated =  Person.updatePerson(person);

            if(isUpdated){
                return Response.ok(Map.of("message","Person Updated","statusCode",204,"isSuccess",true)).build();
            }
            else{
                return Response.ok(Map.of("message","Bad Request","statusCode",400,"isSuccess",false)).build();
            }
        }
        catch(Exception ex){
            return Response.ok(Map.of("message",ex.getMessage(),"statusCode",501,"isSuccess",false)).build();
        }
    }

}