package com.isa.airlinebookingbackend.controller;

import com.isa.airlinebookingbackend.dto.ApiResponse;
import com.isa.airlinebookingbackend.dto.auth.request.*;
import com.isa.airlinebookingbackend.dto.auth.response.AuthenticationResponseDTO;
import com.isa.airlinebookingbackend.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.isa.airlinebookingbackend.constant.Constants.*;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("auth/")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(LOGIN_URL)
    public ResponseEntity<ApiResponse<AuthenticationResponseDTO>> login(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        log.info("login request: {}", authenticationRequestDTO);
        var response = authService.authenticateUser(authenticationRequestDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping(REGISTER_URL)
    public ResponseEntity<ApiResponse<String>> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        log.info("register request: {}", registerRequestDTO);

        return ResponseEntity.ok(authService.createUser(registerRequestDTO));
    }

    @PostMapping(CONTACT_DETAILS_URL)
    public ResponseEntity<ApiResponse<String>> contactDetails(@RequestBody ContactDetailsRequestDTO contactDetailsRequestDTO) {
        log.info("contact details: {}", contactDetailsRequestDTO);
        return ResponseEntity.ok(authService.saveContactDetails(contactDetailsRequestDTO));
    }

    @GetMapping(REFRESH_TOKEN_URL)
    public ResponseEntity<ApiResponse<AuthenticationResponseDTO>> refreshToken(@PathVariable String refreshToken) {
        log.info("refresh token: {}", refreshToken);
        return ResponseEntity.ok(authService.generateAccessTokenWithRefreshToken(refreshToken));
    }

    @PostMapping(CHANGE_PASSWORD_URL)
    public ResponseEntity<ApiResponse<String>> changePassword(@RequestBody ChangePasswordRequestDto changePasswordRequestDto) {
        log.info("Change password request {}", changePasswordRequestDto);
        return ResponseEntity.ok(authService.changePassword(changePasswordRequestDto));
    }

    @PostMapping(FORGOT_PASSWORD_URL)
    public ResponseEntity<ApiResponse<String>> forgotPassword(@RequestBody ForgotPasswordRequestDto requestDto) {
        log.info("Forgot password request {}", requestDto);
        return ResponseEntity.ok(authService.forgotPassword(requestDto));
    }

    @PostMapping(FORGOT_PASSWORD_CONFIRMATION_URL)
    public ResponseEntity<ApiResponse<String>> forgotPasswordConfirmation(@RequestBody ForgotPasswordConfirmationRequestDto requestDto) {
        log.info("Forgot password confirmation request {}", requestDto);
        return ResponseEntity.ok(authService.forgotPasswordConfirmation(requestDto));
    }

    @PostMapping(RESET_PASSWORD_URL)
    public ResponseEntity<ApiResponse<String>> resetPassword(@RequestBody ResetPasswordRequestDto requestDto) {
        log.info("Reset password confirmation request {}", requestDto);
        return ResponseEntity.ok(authService.resetPassword(requestDto));
    }

    @PostMapping(EMAIL_CONFIRMATION_URL)
    public ResponseEntity<ApiResponse<String>> emailConfirmation(@RequestBody EmailConfirmationRequestDto requestDto) {
        log.info("Email confirmation request {}", requestDto);
        return ResponseEntity.ok(authService.emailConfirmation(requestDto));
    }

}
