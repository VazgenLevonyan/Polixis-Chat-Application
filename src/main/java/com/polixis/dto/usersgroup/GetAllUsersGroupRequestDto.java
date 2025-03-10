package com.polixis.dto.usersgroup;

import java.util.ArrayList;
import java.util.List;

public class GetAllUsersGroupRequestDto {
    List<GetUsersGroupRequestDto> groups = new ArrayList<>();

    public List<GetUsersGroupRequestDto> getGroups() {
        return groups;
    }

    public void setGroups(List<GetUsersGroupRequestDto> groups) {
        this.groups = groups;
    }
}
