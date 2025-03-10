package com.polixis.resource;

import com.polixis.dto.usersgroup.*;
import com.polixis.service.UsersGroupService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/group")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsersGroupResource {

    @Inject
    UsersGroupService usersGroupService;

    @POST
    @RolesAllowed("ADMIN")
    public Response creatUsersGroup(CreateUsersGroupRequestDto createUsersGroupRequestDto) {
        String response = usersGroupService.createUsersGroup(createUsersGroupRequestDto);
        return Response.status(Response.Status.CREATED)
                .entity(response)
                .build();
    }

    @PUT
    @Path("/attachUsers")
    @RolesAllowed("ADMIN")
    public Response attachUsersToGroup(AttachUsersToUsersGroupRequest attachUsersToUsersGroupRequest) {
        String response = usersGroupService.attachUsersToGroup(attachUsersToUsersGroupRequest);
        return Response.status(Response.Status.OK)
                .entity(response)
                .build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public Response get(@PathParam("id") Long id) {
        GetUsersGroupRequestDto usersGroup = usersGroupService.getUsersGroupById(id);
        return Response.status(Response.Status.OK)
                .entity(usersGroup)
                .build();
    }

    @GET
    @Path("/all")
    @RolesAllowed("ADMIN")
    public Response getAll() {
        GetAllUsersGroupRequestDto allUsersGroups = usersGroupService.getAllUsersGroups();
        return Response.status(Response.Status.OK)
                .entity(allUsersGroups)
                .build();
    }

    @PUT
    @Path("/detachUsers")
    @RolesAllowed("ADMIN")
    public Response detachUsersToGroup(DetachUsersFromUsersGroup detachUsersFromUsersGroup) {
        String response = usersGroupService.detachUsersFromGroup(detachUsersFromUsersGroup);
        return Response.status(Response.Status.OK)
                .entity(response)
                .build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public Response deleteUsersGroup(@PathParam("id") Long id) {
        String response = usersGroupService.deleteUsersGroup(id);
        return Response.status(Response.Status.OK)
                .entity(response)
                .build();
    }
}
