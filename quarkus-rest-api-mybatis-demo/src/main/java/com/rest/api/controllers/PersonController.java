package com.rest.api.controllers;

import com.rest.api.models.Person;
import com.rest.api.services.PersonService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("/rest/api/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonController {

    @Inject
    private PersonService personService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/hello")
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @GET
    @Path("/")
    public Response getAllPersons() {
        List<Person> persons = personService.getAllPersons();

        try {
            if(persons!=null && persons.size()>0){
                return Response.status(Response.Status.OK)
                        .entity(Map.of("message","Person List","statusCode",200,"isSuccess",true,"data",persons)).build();
            }
            else{
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(Map.of("message","Data Not Found","statusCode",404,"isSuccess",false)).build();
            }
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("message","INTERNAL_SERVER_ERROR","statusCode",501,"isSuccess",false)).build();
        }

    }


    @GET
    @Path("/{id}")
    public Response getPerson(@PathParam("id") Integer id) {
         Person person = personService.getPerson(id);

        try {
            if(person!=null){
                return Response.status(Response.Status.OK)
                        .entity(Map.of("message","Person List","statusCode",200,"isSuccess",true,"data",person)).build();
            }
            else{
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(Map.of("message","Data Not Found","statusCode",404,"isSuccess",false)).build();
            }
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("message","INTERNAL_SERVER_ERROR","statusCode",501,"isSuccess",false)).build();
        }

    }

    @POST
    @Path("/")
    public Response createPerson(Person person) {
        Integer personCreated = personService.createPerson(person);

        try {
            if(personCreated>0){
                return Response.status(Response.Status.CREATED)
                        .entity(Map.of("message","Person Created","statusCode",201,"isSuccess",true)).build();
            }
            else{
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(Map.of("message","Bad Request","statusCode",400,"isSuccess",false)).build();
            }
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("message","INTERNAL_SERVER_ERROR","statusCode",501,"isSuccess",false)).build();
        }

    }

    @PUT
    @Path("/")
    public Response updatePerson(Person person) {
        Integer personUpdated = personService.updatePerson(person);

        try {
            if(personUpdated>0){
                return Response.status(Response.Status.CREATED)
                        .entity(Map.of("message","Person Updated","statusCode",204,"isSuccess",true)).build();
            }
            else{
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(Map.of("message","Bad Request","statusCode",400,"isSuccess",false)).build();
            }
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("message","INTERNAL_SERVER_ERROR","statusCode",501,"isSuccess",false)).build();
        }

    }

    @DELETE
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") Integer id) {
        Integer personDeleted = personService.removePerson(id);
        try {
            if(personDeleted>0){
                return Response.status(Response.Status.CREATED)
                        .entity(Map.of("message","Person Deleted","statusCode",203,"isSuccess",true)).build();
            }
            else{
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(Map.of("message","Bad Request","statusCode",400,"isSuccess",false)).build();
            }
        }
        catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("message","INTERNAL_SERVER_ERROR","statusCode",501,"isSuccess",false)).build();
        }

    }



}