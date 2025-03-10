package com.polixis.service.impl;

import com.polixis.dto.user.CreateUserRequestDto;
import com.polixis.dto.user.CreateUserResponseDto;
import com.polixis.dto.auth.LoginRequest;
import com.polixis.model.User;

import com.polixis.exception.DuplicateException;
import com.polixis.exception.InvalidCredentialsException;
import com.polixis.service.AuthenticationService;
import com.polixis.service.UserService;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;

@ApplicationScoped
public class AuthenticationServiceImpl implements AuthenticationService {

    @Inject
    UserService userService;

    @Transactional
    public CreateUserResponseDto registerUser(CreateUserRequestDto createUserRequestDto) {

        User userByUsername = userService.getUserByUsername(createUserRequestDto.getUsername());
        if(userByUsername != null) {
            throw new DuplicateException("User with username " + createUserRequestDto.getUsername() + " already exists");
        }
        User user = new User();
        user.setName(createUserRequestDto.getName());
        user.setEmail(createUserRequestDto.getEmail());
        user.setUsername(createUserRequestDto.getUsername());
        user.setPassword(BCrypt.hashpw(createUserRequestDto.getPassword(), BCrypt.gensalt()));
        user.setRole(createUserRequestDto.getRole());
       userService.saveUser(user);
        return new CreateUserResponseDto(user);
    }

    public String generateJwt(LoginRequest loginRequest) {
        User user = userService.getUserByUsername(loginRequest.getUsername());
        if (user == null || !BCrypt.checkpw(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        return Jwt.issuer("Messaging System")
                .upn(user.getUsername())
                .groups(user.getRole())
                .subject("getToken")
                .expiresAt(System.currentTimeMillis() + 360000)
                .sign();
    }
}
