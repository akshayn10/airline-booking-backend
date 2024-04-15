package com.isa.airlinebookingbackend.dto.auth.request;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class ForgotPasswordRequestDto {
    @NotEmpty
    private String email;
}
