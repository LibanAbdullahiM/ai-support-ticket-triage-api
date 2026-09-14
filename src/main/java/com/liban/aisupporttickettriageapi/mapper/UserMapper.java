package com.liban.aisupporttickettriageapi.mapper;


import com.liban.aisupporttickettriageapi.dtos.request.UserRequestDTO;
import com.liban.aisupporttickettriageapi.dtos.response.UserResponseDTO;
import com.liban.aisupporttickettriageapi.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserRequestDTO userRequestDTO);

    UserRequestDTO toUserRequestDTO(User user);

    UserResponseDTO toUserResponseDTO(User user);
}
