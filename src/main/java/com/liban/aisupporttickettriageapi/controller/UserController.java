package com.liban.aisupporttickettriageapi.controller;

import com.liban.aisupporttickettriageapi.dtos.request.LoginRequest;
import com.liban.aisupporttickettriageapi.dtos.request.UserRequestDTO;
import com.liban.aisupporttickettriageapi.dtos.response.UserResponseDTO;
import com.liban.aisupporttickettriageapi.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public Map<String, String> login(@Valid @RequestBody LoginRequest request) {

        String generatedJwtToken = userService.verify(request);

        return Map.of("token", generatedJwtToken);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        return new ResponseEntity<>(userService.registerUser(userRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/users/{user_id}/update")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO updateProfile(@PathVariable UUID user_id,
                                         @RequestParam Map<String, String> userRequest) {
        return userService.updateProfile(user_id, userRequest);
    }

    @GetMapping("/users/{user_id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO getUser(@PathVariable UUID user_id) {
        return userService.getUserById(user_id);
    }

    @DeleteMapping("/users/{user_id}/delete")
    public ResponseEntity<?> deleteUser(@PathVariable UUID user_id) {
        userService.deleteUser(user_id);
        return new ResponseEntity<>("User deleted successfully.", HttpStatus.OK);
    }

    @PutMapping("/users/{user_id}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO setRole(@PathVariable UUID user_id,
                                   @RequestParam String role) {
        return userService.setRoleForUser(user_id, role);
    }
}
