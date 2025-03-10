package com.polixis.resource;

import com.polixis.dto.message.CreateMessageRequestDto;
import com.polixis.dto.message.CreateMessageResponseDto;
import com.polixis.dto.message.CreateMessageToAllRequestDto;
import com.polixis.dto.message.CreateSendMessageToUsersGroupRequest;
import com.polixis.service.MessageService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

    @Inject
    MessageService messageService;

    @POST
    @RolesAllowed("ADMIN")
    @Path("/sendToUser")
    public Response sendMessageToUser(CreateMessageRequestDto createMessageRequestDto) {
        try {
            CreateMessageResponseDto message = messageService.sendMessageToUser(createMessageRequestDto);
            return Response.status(Response.Status.CREATED).entity(message).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @RolesAllowed("ADMIN")
    @Path("/sendToAll")
    public Response sendMessageToAll(CreateMessageToAllRequestDto createMessageToAllRequestDto) {
        try {
            String responseMessage = messageService.sendMessageToAll(createMessageToAllRequestDto);
            return Response.status(Response.Status.CREATED).entity(responseMessage).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @RolesAllowed("ADMIN")
    @Path("/sendToGroup")
    public Response sendMessageToGroup(CreateSendMessageToUsersGroupRequest createSendMessageToUsersGroupRequest) {
        String response = messageService.sendMessageToGroup(createSendMessageToUsersGroupRequest);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }
}
