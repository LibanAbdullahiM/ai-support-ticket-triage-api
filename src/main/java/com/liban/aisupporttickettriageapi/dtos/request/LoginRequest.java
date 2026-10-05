package com.liban.aisupporttickettriageapi.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


public record LoginRequest(
        @NotBlank String username,
        @NotBlank String password
) {
}
