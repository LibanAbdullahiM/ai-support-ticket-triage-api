package com.liban.aisupporttickettriageapi.services;

import com.liban.aisupporttickettriageapi.dtos.request.UserRequestDTO;
import com.liban.aisupporttickettriageapi.dtos.response.UserResponseDTO;
import com.liban.aisupporttickettriageapi.exceptions.ResourceAlreadyExistsException;
import com.liban.aisupporttickettriageapi.exceptions.ResourceNotFoundException;
import com.liban.aisupporttickettriageapi.mapper.UserMapper;
import com.liban.aisupporttickettriageapi.model.Role;
import com.liban.aisupporttickettriageapi.model.User;
import com.liban.aisupporttickettriageapi.repositories.RoleRepository;
import com.liban.aisupporttickettriageapi.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper,
                           RoleRepository roleRepository) {
        this.passwordEncoder = new BCryptPasswordEncoder(12);
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {

        if (emailExists(userRequestDTO.getEmail())) {
            throw new ResourceAlreadyExistsException("Email already exists");
        }

        if (usernameExists(userRequestDTO.getUsername())) {
            throw new ResourceAlreadyExistsException("Username already exists");
        }

        User user = userMapper.toUser(userRequestDTO);

        //Encode password
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        Role role = roleRepository.findByRole("USER");

        user.setPassword(encodedPassword);
        user.getRoles().add(role);

        User savedUser = userRepository.save(user);

        return userMapper.toUserResponseDTO(savedUser);
    }

    @Override
    public UserResponseDTO updateProfile(UUID user_id, Map<String, String> userRequests) {

        Optional<User> optionalUser = userRepository.findById(user_id);

        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }

        User user = optionalUser.get();

        String name  = userRequests.get("name");
        String email = userRequests.get("email");
        String username =  userRequests.get("username");

        if (!Objects.equals(email, user.getEmail()) && emailExists(email)) {
            throw new ResourceAlreadyExistsException("Email already exists");
        }

        if (!Objects.equals(username, user.getUsername()) && usernameExists(username)) {
            throw new ResourceAlreadyExistsException("Username already exists");
        }

        user.setName(name);
        user.setEmail(email);
        user.setUsername(username);

        return userMapper.toUserResponseDTO(userRepository.save(user));
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username) != null;
    }

    @Override
    public UserResponseDTO setRoleForUser(UUID user_id, String roleStr) {
        Optional<User> optionalUser = userRepository.findById(user_id);

        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }

        User user = optionalUser.get();
        Role role = roleRepository.findByRole(roleStr);

        if  (role == null) {
            throw new ResourceNotFoundException("Role not found");
        }

        user.getRoles().add(role);

        return userMapper.toUserResponseDTO(userRepository.save(user));
    }

    @Override
    public UserResponseDTO getUserById(UUID user_id) {
        Optional<User> userOptional =  userRepository.findById(user_id);

        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }
        User user = userOptional.get();

        return userMapper.toUserResponseDTO(user);
    }

    @Override
    public void deleteUser(UUID user_id) {
        Optional<User> userOptional =  userRepository.findById(user_id);

        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }
        userRepository.delete(userOptional.get());
    }
}
