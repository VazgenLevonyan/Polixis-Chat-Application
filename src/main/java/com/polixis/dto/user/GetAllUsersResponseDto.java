package com.polixis.dto.user;

import java.util.ArrayList;
import java.util.List;

public class GetAllUsersResponseDto {

    public List<GetUserResponseDto> getUsers() {
        return users;
    }

    public void setUsers(List<GetUserResponseDto> users) {
        this.users = users;
    }

    List<GetUserResponseDto> users = new ArrayList<>();
}
