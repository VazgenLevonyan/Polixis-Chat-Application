package com.polixis.resource;

import com.polixis.dto.auth.LoginRequest;
import com.polixis.dto.user.CreateUserRequestDto;
import com.polixis.dto.user.CreateUserResponseDto;
import com.polixis.service.AuthenticationService;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthenticationResource {

    @Inject
    AuthenticationService authenticationService;
    @Inject
    Logger logger;

    @POST
    @Path("/register")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(CreateUserRequestDto createUserRequestDto) {
        CreateUserResponseDto user = authenticationService.registerUser(createUserRequestDto);
        return Response.status(Response.Status.CREATED)
                .entity(user)
                .build();
    }

    @GET
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public Response getJwt(LoginRequest loginRequest) {
        String jwt = authenticationService.generateJwt(loginRequest);
        return Response.ok(jwt).build();
    }
}
