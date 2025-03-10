package com.polixis.dto.usersgroup;

import com.polixis.dto.user.CreateUserResponseDto;

import java.util.List;

public class GetUsersGroupRequestDto {

    private Long id;
    private String name;
    private List<CreateUserResponseDto> members;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CreateUserResponseDto> getMembers() {
        return members;
    }

    public void setMembers(List<CreateUserResponseDto> members) {
        this.members = members;
    }
}