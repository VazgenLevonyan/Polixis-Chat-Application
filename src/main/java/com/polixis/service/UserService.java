package com.polixis.service;

import com.polixis.dto.user.CreateUserResponseDto;
import com.polixis.dto.user.GetAllUsersResponseDto;
import com.polixis.dto.user.GetUserResponseDto;
import com.polixis.dto.user.UpdateUserDto;
import com.polixis.entity.User;

import java.util.List;

public interface UserService {
    GetUserResponseDto getUserById(Long id);

    CreateUserResponseDto updateUser(UpdateUserDto updateUserDto);

    String deleteUser(Long id);

    User getUser(Long id);

    List<User> getAllUsers();

    void saveUser(User user);

    User getUserByUsername(String username);

    GetAllUsersResponseDto getUsersList();
}
