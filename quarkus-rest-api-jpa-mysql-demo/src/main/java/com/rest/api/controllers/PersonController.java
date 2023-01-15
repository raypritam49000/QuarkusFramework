package com.rest.api.controllers;

import com.rest.api.entity.Person;
import com.rest.api.models.PersonDto;
import com.rest.api.services.PersonService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("/api/persons")
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
    public Response createPerson(PersonDto person) {
        try {
            if (person != null && !person.getName().equals("") && !person.getCity().equals("") && !person.getSalary().equals("")) {
                PersonDto createdPerson = this.personService.createPerson(person);
                return Response.ok(Map.of("message", "Person Created", "statusCode", 201, "isSuccess", true, "data", createdPerson)).build();
            } else {
                return Response.ok(Map.of("message", "All Parameter rae required", "statusCode", 400, "isSuccess", false)).build();
            }
        } catch (Exception ex) {
            return Response.ok(Map.of("message", ex.getMessage(), "statusCode", 501, "isSuccess", false)).build();
        }
    }

    @GET
    @Path("/")
    public Response getAllPersons() {
        try {
            List<PersonDto> persons = this.personService.getAllPersons();
            if (persons != null && persons.size() > 0 && !persons.isEmpty()) {
                return Response.ok(Map.of("message", "Person List", "statusCode", 200, "isSuccess", true, "data", persons)).build();
            } else {
                return Response.ok(Map.of("message", "All Parameter rae required", "statusCode", 400, "isSuccess", false, "data", persons)).build();
            }
        } catch (Exception ex) {
            return Response.ok(Map.of("message", ex.getMessage(), "statusCode", 501, "isSuccess", false)).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getPerson(@PathParam("id") Long id) {
        try {
            PersonDto person = this.personService.getPerson(id);

            if (person != null && !person.getName().equals("") && !person.getCity().equals("") && !person.getSalary().equals("") && person.getId() != null) {
                return Response.ok(Map.of("message", "Person List", "statusCode", 200, "isSuccess", true, "data", person)).build();
            } else {
                return Response.ok(Map.of("message", "Data Not Found", "statusCode", 400, "isSuccess", false, "data", person)).build();
            }
        } catch (Exception ex) {
            return Response.ok(Map.of("message", ex.getMessage(), "statusCode", 501, "isSuccess", false)).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") Long id) {
        try {
            Boolean isDeleted = this.personService.deletePerson(id);

            if (isDeleted) {
                return Response.ok(Map.of("message", "Person Deleted", "statusCode", 204, "isSuccess", true)).build();
            } else {
                return Response.ok(Map.of("message", "Error Occur Where Delete Person", "statusCode", 400, "isSuccess", false)).build();
            }
        } catch (Exception ex) {
            return Response.ok(Map.of("message", ex.getMessage(), "statusCode", 501, "isSuccess", false)).build();
        }
    }


    @PUT
    @Path("/{id}")
    public Response updatePerson(@PathParam("id") Long id, PersonDto person) {
        try {
            if (person != null && !person.getName().equals("") && !person.getCity().equals("") && !person.getSalary().equals("") && id != null) {

                PersonDto updatePerson = personService.updatePerson(id, person);
                if (updatePerson != null) {
                    return Response.ok(Map.of("message", "Person Updated", "statusCode", 204, "isSuccess", true, "data", updatePerson)).build();
                } else {
                    return Response.ok(Map.of("message", "Error Occur Where Delete Person", "statusCode", 400, "isSuccess", false)).build();
                }

            } else {
                return Response.ok(Map.of("message", "All Parameter rae required", "statusCode", 400, "isSuccess", false)).build();
            }

        } catch (Exception ex) {
            return Response.ok(Map.of("message", ex.getMessage(), "statusCode", 501, "isSuccess", false)).build();
        }
    }


}