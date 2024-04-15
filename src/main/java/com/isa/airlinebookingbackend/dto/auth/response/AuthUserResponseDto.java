package com.isa.airlinebookingbackend.dto.auth.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserResponseDto {
    private String email;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;
    private boolean isEnabled;
    private boolean isAccountNonLocked;
}
