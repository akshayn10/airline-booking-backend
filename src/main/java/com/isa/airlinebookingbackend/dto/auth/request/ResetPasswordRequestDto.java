package com.isa.airlinebookingbackend.dto.auth.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class ResetPasswordRequestDto {
    @NotEmpty
    private String email;
    @NotEmpty
    @NotNull
    private String password;
}
