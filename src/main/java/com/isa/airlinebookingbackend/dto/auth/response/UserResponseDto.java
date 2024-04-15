package com.isa.airlinebookingbackend.dto.auth.response;

import com.isa.airlinebookingbackend.entity.auth.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String addressLine;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;
    private String phoneNumberPrefix;
    private String phoneNumber;
}
