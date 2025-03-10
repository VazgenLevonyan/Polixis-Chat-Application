package com.polixis.service.impl;

import com.polixis.dto.message.CreateMessageRequestDto;
import com.polixis.dto.message.CreateMessageResponseDto;
import com.polixis.dto.message.CreateMessageToAllRequestDto;
import com.polixis.dto.message.CreateSendMessageToUsersGroupRequest;
import com.polixis.model.Message;
import com.polixis.model.User;
import com.polixis.model.UsersGroup;
import com.polixis.repository.MessageRepository;
import com.polixis.service.MessageService;
import com.polixis.service.UserService;
import com.polixis.service.UsersGroupService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class MessageServiceImpl implements MessageService {

    @Inject
    UserService userService;
    @Inject
    UsersGroupService usersGroupService;
    @Inject
    MessageRepository messageRepository;


    @Override
    @Transactional
    public CreateMessageResponseDto sendMessageToUser(CreateMessageRequestDto createMessageRequestDto) {
        User sender = userService.getUser(createMessageRequestDto.getSenderId());
        User recipient = userService.getUser(createMessageRequestDto.getRecipientId());

        Message message = new Message();
        message.sender = sender;
        message.content = createMessageRequestDto.getContent();
        message.timestamp = LocalDateTime.now();
        message.recipientId = recipient.id;
        messageRepository.persist(message);
        return new CreateMessageResponseDto(message);
    }

    @Transactional
    public String sendMessageToGroup(CreateSendMessageToUsersGroupRequest createSendMessageToUsersGroupRequest) {
        User sender = userService.getUser(createSendMessageToUsersGroupRequest.getSenderId());
        UsersGroup usersGroup = usersGroupService.getUserGroup(createSendMessageToUsersGroupRequest.getGroupId());

        for (User member : usersGroup.getMembers()) {
            Message message = new Message();
            message.sender = sender;
            message.content = createSendMessageToUsersGroupRequest.getContent();
            message.timestamp = LocalDateTime.now();
            message.setRecipientId(member.getId());
            messageRepository.persist(message);
        }
        return "Message successfully sent to group";
    }

    @Override
    @Transactional
    public String sendMessageToAll(CreateMessageToAllRequestDto createMessageToAllRequestDto) {
        User sender = userService.getUser(createMessageToAllRequestDto.getSenderId());
        List<User> userList = userService.getAllUsers();
        for (User user : userList) {
            Message message = new Message();
            message.sender = sender;
            message.content = createMessageToAllRequestDto.getContent();
            message.timestamp = LocalDateTime.now();
            message.recipientId = user.id;
            messageRepository.persist(message);
        }
        return "Message sent all users successfully";
    }
}
