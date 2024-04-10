package com.isa.airlinebookingbackend.service.implementation.auth;

import com.isa.airlinebookingbackend.entity.auth.RefreshToken;
import com.isa.airlinebookingbackend.entity.auth.User;
import com.isa.airlinebookingbackend.exception.auth.RefreshTokenExpiredException;
import com.isa.airlinebookingbackend.exception.auth.RefreshTokenNotFoundException;
import com.isa.airlinebookingbackend.repository.RefreshTokenRepository;
import com.isa.airlinebookingbackend.security.JwtConfig;
import com.isa.airlinebookingbackend.service.auth.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtConfig jwtConfig;

    @Override
    public String createRefreshToken(User user) {
        RefreshToken refreshToken = refreshTokenRepository.findByUser(user).orElse(null);
        if (refreshToken != null) {
            refreshToken.setToken(getUUID());
            refreshToken.setExpiryDate(getExpiryDate());
        } else {
            refreshToken = RefreshToken.builder()
                    .user(user)
                    .token(getUUID())
                    .expiryDate(getExpiryDate())
                    .build();
        }
        refreshTokenRepository.save(refreshToken);
        return refreshToken.getToken();
    }


    @Override
    public RefreshToken findByToken(String token) {
        return refreshTokenRepository.findByToken(token).orElseThrow(() -> new RefreshTokenNotFoundException("Refresh token Token not found"));
    }

    @Override
    public void verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RefreshTokenExpiredException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
    }

    private String getUUID() {
        return UUID.randomUUID().toString();
    }

    private Instant getExpiryDate() {
        return Instant.now().plusMillis(jwtConfig.getRefreshExpiration());
    }

}