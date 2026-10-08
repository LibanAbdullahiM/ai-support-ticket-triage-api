package com.liban.aisupporttickettriageapi.dtos.response;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
public class UserResponseDTO {

    private UUID id;
    private String name;
    private String email;
    private String username;

    private Set<String> roles;
}
