package com.polixis.resource;

import com.polixis.dto.user.CreateUserResponseDto;
import com.polixis.dto.user.GetUserResponseDto;
import com.polixis.dto.user.UpdateUserDto;
import com.polixis.model.User;
import com.polixis.service.UserService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {


    @Inject
    UserService userService;

    @GET
    @RolesAllowed("ADMIN")
    @Path("/{id}")
    public Response getUserById(@PathParam("id") Long id) {
        GetUserResponseDto user = userService.getUserById(id);
        return Response.status(Response.Status.OK)
                .entity(user)
                .build();
    }

    @GET
    @RolesAllowed("ADMIN")
    @Path("/all")
    public Response getAll() {
        List<User> allUsers = userService.getAllUsers();
        return Response.status(Response.Status.OK)
                .entity(allUsers)
                .build();
    }

    @PUT
    @RolesAllowed("ADMIN")
    @Path("/update")
    public Response update(UpdateUserDto updateUserDto) {
        CreateUserResponseDto createUserResponseDto = userService.updateUser(updateUserDto);
        return Response.status(Response.Status.OK)
                .entity(createUserResponseDto)
                .build();
    }

    @DELETE
    @RolesAllowed("ADMIN")
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        String response = userService.deleteUser(id);
        return Response.status(Response.Status.OK)
                .entity(response)
                .build();
    }
}
