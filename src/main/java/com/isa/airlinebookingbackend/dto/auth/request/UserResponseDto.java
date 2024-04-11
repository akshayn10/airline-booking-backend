package com.isa.airlinebookingbackend.dto.auth.request;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String email;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;
    private boolean isEnabled;
    private boolean isAccountNonLocked;
}
