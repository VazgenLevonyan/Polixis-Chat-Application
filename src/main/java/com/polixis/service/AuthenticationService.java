package com.polixis.service;

import com.polixis.dto.auth.LoginRequest;
import com.polixis.dto.user.CreateUserRequestDto;
import com.polixis.dto.user.CreateUserResponseDto;

public interface AuthenticationService {

    CreateUserResponseDto registerUser(CreateUserRequestDto createUserRequestDto);

    String generateJwt(LoginRequest loginRequest);
}
