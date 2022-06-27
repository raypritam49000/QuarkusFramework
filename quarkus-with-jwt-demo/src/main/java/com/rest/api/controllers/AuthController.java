package com.rest.api.controllers;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.rest.api.dto.UserDto;
import com.rest.api.entity.User;
import com.rest.api.service.UserService;
import com.rest.api.utils.JwtUtils;
import com.rest.api.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;


@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtils jwtUtils;

	@POST
	@Path("/login")
	public Response login(UserDto userDto) {

		try {
			User user = userService.getUserByUsername(userDto.getUsername());
			System.out.println(user);

			if (user == null) {
				return Response.status(Response.Status.NOT_FOUND).entity(Map.of("statusCode",404,"message", "User Not Found", "success", false))
						.build();
			} else {

				if (passwordEncoder.checkPassword(userDto.getPassword(), user.getPassword())) {

					String token = jwtUtils.generateTokenFromUsername(user.getUsername());
					
					return Response.status(Response.Status.OK).entity(Map.of("statusCode",200,"token", token, "type", "Bearer", "success", true, "body", user))
							.build();
				} else {
					return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("statusCode",400,"success", false,"message","Bad Request"))
							.build();
				}
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of("statusCode",501,"success", true,"message","Internal Server Error"))
					.build();
		}
	}

	@POST
	@Path("/register")
	public Response register(User user) {
		try {

			String encodedPassword = passwordEncoder.encodePassword(user.getPassword());
			user.setPassword(encodedPassword);

			User registerUser = this.userService.createUser(user);

			if (user!=null && !user.getUsername().equals("")) {
				return Response.ok(Map.of("message", "User Registered", "success", true,"data",registerUser)).build();
			} else {
				return Response.ok(Map.of("message", "Error Occur While User Registerd", "success", false)).build();
			}
		} catch (Exception e) {
			return Response.ok(Map.of("message", e.getMessage(), "success", false)).build();
		}
	}

}
