package com.rest.api.controllers;

import com.rest.api.filters.JWTTokenNeeded;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/echo")
@Produces(MediaType.TEXT_PLAIN)
public class EchoEndpoint {

	@GET
	public Response echo(@QueryParam("message") String message) {
		return Response.ok().entity(message == null ? "no message" : message).build();
	}

	@GET
	@Path("jwt")
	@JWTTokenNeeded
	public Response echoWithJWTToken(@QueryParam("message") String message) {
		return Response.ok().entity(message == null ? "no message" : message).build();
	}
}