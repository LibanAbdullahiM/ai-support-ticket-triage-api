package com.liban.aisupporttickettriageapi.services;

import com.liban.aisupporttickettriageapi.dtos.request.UserRequestDTO;
import com.liban.aisupporttickettriageapi.dtos.response.UserResponseDTO;

import java.util.Map;
import java.util.UUID;

public interface UserService {

    UserResponseDTO registerUser(UserRequestDTO userRequestDTO);

    UserResponseDTO updateProfile(UUID user_id, Map<String, String> userRequests);

    boolean emailExists(String email);

    boolean usernameExists(String username);

    UserResponseDTO setRoleForUser(UUID user_id, String role);

    UserResponseDTO getUserById(UUID user_id);

    void deleteUser(UUID user_id);
}
