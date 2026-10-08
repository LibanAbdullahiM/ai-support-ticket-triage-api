package com.liban.aisupporttickettriageapi.mapper;


import com.liban.aisupporttickettriageapi.dtos.request.UserRequestDTO;
import com.liban.aisupporttickettriageapi.dtos.response.UserResponseDTO;
import com.liban.aisupporttickettriageapi.model.Role;
import com.liban.aisupporttickettriageapi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserRequestDTO userRequestDTO);

    UserRequestDTO toUserRequestDTO(User user);

    @Mapping(target = "roles", source = "roles")
    UserResponseDTO toUserResponseDTO(User user);

    default String map(Role role) {
        return role.getRole();
    }
}
