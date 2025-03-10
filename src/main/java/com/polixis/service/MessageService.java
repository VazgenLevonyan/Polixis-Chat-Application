package com.polixis.service;

import com.polixis.dto.message.CreateMessageRequestDto;
import com.polixis.dto.message.CreateMessageResponseDto;
import com.polixis.dto.message.CreateMessageToAllRequestDto;
import com.polixis.dto.message.CreateSendMessageToUsersGroupRequest;

public interface MessageService {

    CreateMessageResponseDto sendMessageToUser(CreateMessageRequestDto createMessageRequestDto);

    String sendMessageToGroup(CreateSendMessageToUsersGroupRequest createSendMessageToUsersGroupRequest);

    String sendMessageToAll(CreateMessageToAllRequestDto createMessageToAllRequestDto);
}
