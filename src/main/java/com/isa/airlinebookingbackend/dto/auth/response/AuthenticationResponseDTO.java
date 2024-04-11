package com.isa.airlinebookingbackend.dto.auth.response;

import com.isa.airlinebookingbackend.dto.auth.request.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponseDTO {
    private String accessToken;
    private String refreshToken;
    private UserResponseDto user;
}
