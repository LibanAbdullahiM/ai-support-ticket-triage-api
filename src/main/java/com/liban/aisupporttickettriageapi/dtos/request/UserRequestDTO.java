package com.liban.aisupporttickettriageapi.dtos.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {

    private String name;
    private String email;

    private String username;
    private String password;
}
