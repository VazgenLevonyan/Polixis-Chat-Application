package com.polixis.dto.message;

import com.polixis.model.Message;

import java.time.LocalDateTime;

public class CreateMessageResponseDto {

    Long id;
    Long senderId;
    Long receiverId;
    String content;
    LocalDateTime timestamp;

    public CreateMessageResponseDto(Message message) {
        this.id = message.getId();
        this.senderId = message.getSender().getId();
        this.receiverId = message.getRecipientId();
        this.content = message.getContent();
        this.timestamp = message.getTimestamp();
    }

    public CreateMessageResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
