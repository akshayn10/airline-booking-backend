package com.isa.airlinebookingbackend.service.auth;


import com.isa.airlinebookingbackend.dto.ApiResponse;
import com.isa.airlinebookingbackend.dto.auth.request.*;
import com.isa.airlinebookingbackend.dto.auth.response.AuthenticationResponseDTO;

public interface AuthService {
    ApiResponse<String> createUser(RegisterRequestDTO registerRequestDTO);
    ApiResponse<AuthenticationResponseDTO> authenticateUser(AuthenticationRequestDTO authenticationRequestDTO);
    ApiResponse<AuthenticationResponseDTO> generateAccessTokenWithRefreshToken(String refreshToken);
    ApiResponse<String> forgotPassword(ForgotPasswordRequestDto requestDto);
    ApiResponse<String> forgotPasswordConfirmation(ForgotPasswordConfirmationRequestDto requestDto);
    ApiResponse<String> resetPassword(ResetPasswordRequestDto requestDto);
    ApiResponse<String> emailConfirmation(EmailConfirmationRequestDto requestDto);
    ApiResponse<String> changePassword(ChangePasswordRequestDto changePasswordRequestDto);
    ApiResponse<String> saveContactDetails(ContactDetailsRequestDTO contactDetailsRequestDTO);
}
