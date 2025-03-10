package com.polixis.service.impl;

import com.polixis.dto.user.CreateUserResponseDto;
import com.polixis.dto.usersgroup.*;
import com.polixis.model.User;
import com.polixis.model.UsersGroup;
import com.polixis.exception.DuplicateException;
import com.polixis.exception.NotFoundException;
import com.polixis.repository.UsersGroupRepository;
import com.polixis.service.UserService;
import com.polixis.service.UsersGroupService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UsersGroupServiceImpl implements UsersGroupService {

    @Inject
    UserService userService;
    @Inject
    UsersGroupRepository usersGroupRepository;

    @Override
    @Transactional
    public String createUsersGroup(CreateUsersGroupRequestDto createUsersGroupRequestDto) {
        UsersGroup group = usersGroupRepository.findByName(createUsersGroupRequestDto.getName());
        if (group != null) {
            throw new DuplicateException("Group with name " + createUsersGroupRequestDto.getName() + " already exists");
        }
        UsersGroup usersGroup = new UsersGroup();
        usersGroup.setName(createUsersGroupRequestDto.getName());
        usersGroupRepository.persist(usersGroup);
        return usersGroup.getName() + " group was created";
    }

    @Override
    @Transactional
    public String attachUsersToGroup(AttachUsersToUsersGroupRequest attachUsersToUsersGroupRequest) {
        UsersGroup usersGroup = usersGroupRepository.findById(attachUsersToUsersGroupRequest.getGroupId());

        for (Long userId : attachUsersToUsersGroupRequest.getMemberIds()) {
            User user = userService.getUser(userId);
            if (!usersGroup.getMembers().contains(user)) {
                usersGroup.getMembers().add(user);
            }
            if (!user.getGroups().contains(usersGroup)) {
                user.getGroups().add(usersGroup);
            }
            userService.saveUser(user);
        }
        usersGroupRepository.persist(usersGroup);
        return "Users successfully attached to group " + usersGroup.getName();
    }

    @Override
    @Transactional
    public String detachUsersFromGroup(DetachUsersFromUsersGroup detachUsersFromUsersGroup) {
        UsersGroup usersGroup = usersGroupRepository.findById(detachUsersFromUsersGroup.getGroupId());

        for (Long userId : detachUsersFromUsersGroup.getMemberIds()) {
            User user = userService.getUser(userId);
            usersGroup.getMembers().remove(user);
            user.getGroups().remove(usersGroup);
            userService.saveUser(user);
        }
        usersGroupRepository.persist(usersGroup);
        return "Users successfully detached from group " + usersGroup.getName();
    }

    @Override
    public UsersGroup getUserGroup(Long groupId) {
        UsersGroup usersGroup = usersGroupRepository.findById(groupId);
        if (usersGroup == null) {
            throw new NotFoundException("Users group with id " + groupId + " not found");
        }
        return usersGroup;
    }

    @Override
    @Transactional
    public GetUsersGroupRequestDto getUsersGroupById(Long id) {
        UsersGroup group = getUserGroup(id);
        GetUsersGroupRequestDto getUsersGroupRequestDto = new GetUsersGroupRequestDto();
        getUsersGroupRequestDto.setId(group.getId());
        getUsersGroupRequestDto.setName(group.getName());
        List<CreateUserResponseDto> memberDtos = group.getMembers().stream()
                .map(CreateUserResponseDto::new)
                .collect(Collectors.toList());
        getUsersGroupRequestDto.setMembers(memberDtos);

        return getUsersGroupRequestDto;
    }

    @Override
    public GetAllUsersGroupRequestDto getAllUsersGroups() {
        GetAllUsersGroupRequestDto getAllUsersGroupRequestDto = new GetAllUsersGroupRequestDto();
        List<UsersGroup> groupList = usersGroupRepository.listAll();
        groupList.forEach(group -> {
            GetUsersGroupRequestDto getUsersGroupRequestDto = new GetUsersGroupRequestDto();
            getUsersGroupRequestDto.setId(group.getId());
            getUsersGroupRequestDto.setName(group.getName());
            List<CreateUserResponseDto> memberDtos = group.getMembers().stream()
                    .map(CreateUserResponseDto::new)
                    .collect(Collectors.toList());
            getUsersGroupRequestDto.setMembers(memberDtos);
            getAllUsersGroupRequestDto.getGroups().add(getUsersGroupRequestDto);
        });
        return getAllUsersGroupRequestDto;
    }

    @Override
    @Transactional
    public String deleteUsersGroup(Long groupId) {
        UsersGroup usersGroup = usersGroupRepository.findById(groupId);
        String usersGroupName = usersGroup.getName();
        usersGroupRepository.delete(usersGroup);
        return usersGroupName + " group successfully deleted";
    }
}
