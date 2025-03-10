package com.polixis.service.impl;

import com.polixis.dto.user.CreateUserResponseDto;
import com.polixis.dto.user.GetAllUsersResponseDto;
import com.polixis.dto.user.GetUserResponseDto;
import com.polixis.dto.user.UpdateUserDto;
import com.polixis.entity.User;
import com.polixis.exception.NotFoundException;
import com.polixis.repository.UserRepository;
import com.polixis.service.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    UserRepository userRepository;

    @Transactional
    @Override
    public GetUserResponseDto getUserById(Long id) {
        User user = getUser(id);
        return new GetUserResponseDto(user);
    }

    @Transactional
    @Override
    public CreateUserResponseDto updateUser(UpdateUserDto updateUserDto) {
        User user = getUser(updateUserDto.getId());
        user.setName(updateUserDto.getName());
        userRepository.persist(user);
        return new CreateUserResponseDto(user);
    }

    @Transactional
    @Override
    public String deleteUser(Long id) {
        User user = getUser(id);
        userRepository.delete(user);
        return "User with id " + id + " was deleted";
    }

    @Transactional
    @Override
    public User getUser(Long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new NotFoundException("User with id " + id + " not found");
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.listAll();
    }

    @Override
    public void saveUser(User user) {
        userRepository.persist(user);
    }

    @Override
    public User getUserByUsername(String username) {
       return userRepository.findByUsername(username);
    }

    @Override
    public GetAllUsersResponseDto getUsersList() {
        GetAllUsersResponseDto getAllUsersResponseDto = new GetAllUsersResponseDto();
        List<User> userList = userRepository.listAll();
        userList.forEach(user -> {
            GetUserResponseDto getUserResponseDto = new GetUserResponseDto(user);
            getAllUsersResponseDto.getUsers().add(getUserResponseDto);
        });
        return getAllUsersResponseDto;
    }
}
