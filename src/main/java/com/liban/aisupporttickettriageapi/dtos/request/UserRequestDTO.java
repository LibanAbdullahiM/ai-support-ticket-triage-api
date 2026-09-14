package com.liban.aisupporttickettriageapi.dtos.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {

    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @NotBlank
    @Email(message = "Invalid email format")
    private String email;

    @Getter
    @Size(min = 3, max = 100)
    private String username;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;
}
