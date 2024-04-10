package com.isa.airlinebookingbackend.dto.auth.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class RegisterRequestDTO {
    @NotEmpty(message = "first name is required")
    private String firstName;
    @NotEmpty(message = "last name is required")
    private String lastName;
    @NotEmpty(message = "email is required")
    private String email;
    @NotEmpty(message = "password is required")
    private String password;
    @NotEmpty (message = "username is required")
    private String username;
}
