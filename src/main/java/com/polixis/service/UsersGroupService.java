package com.polixis.service;

import com.polixis.dto.usersgroup.*;
import com.polixis.model.UsersGroup;

public interface UsersGroupService {

    String createUsersGroup(CreateUsersGroupRequestDto createUsersGroupRequestDto);

    String deleteUsersGroup(Long groupId);

    String attachUsersToGroup(AttachUsersToUsersGroupRequest attachUsersToUsersGroupRequest);

    String detachUsersFromGroup(DetachUsersFromUsersGroup detachUsersFromUsersGroup);

    UsersGroup getUserGroup(Long groupId);

    GetUsersGroupRequestDto getUsersGroupById(Long id);

    GetAllUsersGroupRequestDto getAllUsersGroups();
}
