package com.liban.aisupporttickettriageapi.mapper;


import com.liban.aisupporttickettriageapi.dtos.request.RoleRequestDTO;
import com.liban.aisupporttickettriageapi.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role toRole(RoleRequestDTO roleRequestDTO);

    RoleRequestDTO toRoleRequestDTO(Role role);
}
