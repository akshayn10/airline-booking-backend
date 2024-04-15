package com.isa.airlinebookingbackend.dto.auth.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class AuthenticationRequestDTO {
    @NotNull(message = "Email cannot be Null")
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email is not valid")
    private String email;
    @NotNull
    @NotEmpty(message = "Password cannot be empty or Null")
    private String password;
}
