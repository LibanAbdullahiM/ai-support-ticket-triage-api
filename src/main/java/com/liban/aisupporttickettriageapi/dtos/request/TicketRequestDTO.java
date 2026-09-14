package com.liban.aisupporttickettriageapi.dtos.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketRequestDTO {

    @NotBlank
    @Size(min = 25, max = 255)
    private String title;

    @Size(min = 100, max = 999, message = "Description must be minimum length of 100 characters")
    private String description;
}
