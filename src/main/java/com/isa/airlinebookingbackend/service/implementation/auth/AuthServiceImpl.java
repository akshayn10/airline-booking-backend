package com.isa.airlinebookingbackend.service.implementation.auth;


import com.isa.airlinebookingbackend.dto.ApiResponse;
import com.isa.airlinebookingbackend.dto.auth.request.*;
import com.isa.airlinebookingbackend.dto.auth.response.AuthenticationResponseDTO;
import com.isa.airlinebookingbackend.entity.auth.OTP;
import com.isa.airlinebookingbackend.entity.auth.RefreshToken;
import com.isa.airlinebookingbackend.entity.auth.Role;
import com.isa.airlinebookingbackend.entity.auth.User;
import com.isa.airlinebookingbackend.exception.auth.OTPMismatchException;
import com.isa.airlinebookingbackend.exception.auth.OTPNotVerifiedException;
import com.isa.airlinebookingbackend.exception.auth.PasswordMisMatchException;
import com.isa.airlinebookingbackend.exception.auth.UserNotFoundException;
import com.isa.airlinebookingbackend.repository.UserRepository;
import com.isa.airlinebookingbackend.service.auth.*;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.isa.airlinebookingbackend.constant.Constants.*;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final EmailService emailService;
    private final OTPService otpService;


    @Override
    public ApiResponse<String> createUser(RegisterRequestDTO registerRequestDTO) {
        User user = User.builder()
                .username(registerRequestDTO.getUsername())
                .password(passwordEncoder.encode(registerRequestDTO.getPassword()))
                .email(registerRequestDTO.getEmail())
                .role(Role.USER)
                .isAccountNonLocked(true)
                .firstName(registerRequestDTO.getFirstName())
                .lastName(registerRequestDTO.getLastName())
                .build();
        userRepository.save(user);
        String otp = otpService.createOTP(user);
        try {
            emailService.sendEmail(user.getEmail(), CONFIRM_EMAIL_SUBJECT, otp);
        } catch (MessagingException e) {
            log.info(e.getMessage());
        }
        log.info("user created: {}", user);
        return ApiResponse.success("OTP sent to email " + user.getEmail());

    }

    @Override
    public ApiResponse<AuthenticationResponseDTO> authenticateUser(AuthenticationRequestDTO request) {
        log.info("{} {}", request.getPassword(), request.getEmail());
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        log.info("user authenticated: {}", authentication);

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not exist"));
        var userResponseDto = UserResponseDto.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .createdAt(user.getCreatedAt())
                .lastLogin(user.getLastLogin())
                .isEnabled(user.isEnabled())
                .isAccountNonLocked(user.isAccountNonLocked())
                .build();
        var jwtToken = jwtService.generateToken(user.getEmail());
        var refreshToken = refreshTokenService.createRefreshToken(user);
        return ApiResponse.success(AuthenticationResponseDTO.builder()
                .accessToken(jwtToken)
                .user(userResponseDto)
                .refreshToken(refreshToken)
                .build());
    }

    @Override
    public AuthenticationResponseDTO generateAccessTokenWithRefreshToken(String refreshToken) {
        RefreshToken refreshTokenFromRepo = refreshTokenService.findByToken(refreshToken);
        refreshTokenService.verifyExpiration(refreshTokenFromRepo);
        var user = refreshTokenFromRepo.getUser();
        var userResponseDto = UserResponseDto.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .createdAt(user.getCreatedAt())
                .lastLogin(user.getLastLogin())
                .isEnabled(user.isEnabled())
                .isAccountNonLocked(user.isAccountNonLocked())
                .build();
        var jwtToken = jwtService.generateToken(user.getEmail());
        return AuthenticationResponseDTO.builder()
                .accessToken(jwtToken)
                .user(userResponseDto)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public ApiResponse<String> forgotPassword(ForgotPasswordRequestDto requestDto) {
        var user = userRepository.findByEmail(requestDto.getEmail()).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        String otp = otpService.createOTP(user);
        try {
            emailService.sendEmail(user.getEmail(), RESET_PASSWORD_SUBJECT, otp);
        } catch (MessagingException e) {
            log.info(e.getMessage());
        }
        return ApiResponse.success("OTP sent to email " + user.getEmail());
    }

    @Override
    public ApiResponse<String> forgotPasswordConfirmation(ForgotPasswordConfirmationRequestDto requestDto) {
        var user = userRepository.findByEmail(requestDto.getEmail()).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        OTP otp = otpService.findOTPByUser(user);
        otpService.verifyExpiration(otp);
        if (!requestDto.getOtp().equals(otp.getOtp())) {
            throw new OTPMismatchException("OTP does not match");
        }
        otpService.verifyOTP(otp);
        return ApiResponse.success("OTP Verification successful");
    }

    @Override
    public ApiResponse<String> resetPassword(ResetPasswordRequestDto requestDto) {
        var user = userRepository.findByEmail(requestDto.getEmail()).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        if (!otpService.findOTPByUser(user).isVerified()) {
            throw new OTPNotVerifiedException("OTP is not verified");
        }
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        otpService.deleteByUser(user);
        userRepository.save(user);
        return ApiResponse.success("Password Reset successful");
    }

    @Override
    public ApiResponse<String> emailConfirmation(EmailConfirmationRequestDto requestDto) {
        var user = userRepository.findByEmail(requestDto.getEmail()).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        OTP otp = otpService.findOTPByUser(user);
        otpService.verifyExpiration(otp);
        if (!requestDto.getOtp().equals(otp.getOtp())) {
            throw new OTPMismatchException("OTP does not match");
        }
        otpService.verifyOTP(otp);
        user.setEnabled(true);
        userRepository.save(user);
        otpService.deleteByUser(user);
        return ApiResponse.success("Email Confirmation Successful");
    }

    @Override
    public ApiResponse<String> changePassword(ChangePasswordRequestDto requestDto) {
        var user = userRepository.findByEmail(requestDto.getEmail()).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        if (!passwordEncoder.matches(requestDto.getOldPassword(), user.getPassword())) {
            throw new PasswordMisMatchException("Wrong password");
        }
        user.setPassword(passwordEncoder.encode(requestDto.getNewPassword()));
        userRepository.save(user);
        return ApiResponse.success("Password changed successfully");
    }
}
