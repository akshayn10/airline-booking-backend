package com.isa.airlinebookingbackend.dto.auth.request;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class ChangePasswordRequestDto {
    @NotEmpty(message = "Email Cannot be empty")
    private String email;
    @NotEmpty(message = "Old password is required")
    private String oldPassword;
    @NotEmpty(message = "New password is required")
    private String newPassword;
}
