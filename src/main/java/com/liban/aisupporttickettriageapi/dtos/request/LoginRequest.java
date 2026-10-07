package com.liban.aisupporttickettriageapi.dtos.request;

import jakarta.validation.constraints.NotBlank;


public record LoginRequest(
        @NotBlank String username,
        @NotBlank String password
) {
}
