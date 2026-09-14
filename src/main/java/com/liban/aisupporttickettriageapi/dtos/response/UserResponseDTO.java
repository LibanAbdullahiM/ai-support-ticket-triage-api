package com.liban.aisupporttickettriageapi.dtos.response;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserResponseDTO {

    private String name;
    private String email;
    private String username;
}
