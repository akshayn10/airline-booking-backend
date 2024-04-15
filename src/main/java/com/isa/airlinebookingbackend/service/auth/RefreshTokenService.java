package com.isa.airlinebookingbackend.service.auth;


import com.isa.airlinebookingbackend.entity.auth.RefreshToken;
import com.isa.airlinebookingbackend.entity.auth.User;

public interface RefreshTokenService {
    String createRefreshToken(User user);

    RefreshToken findByToken(String token);

    void verifyExpiration(RefreshToken token);
}
