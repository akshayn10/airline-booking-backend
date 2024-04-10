package com.isa.airlinebookingbackend.service.auth;

public interface JwtService {

    public String extractUsername(String token);
    public boolean validateToken(String token);
    public String generateToken(String email);


}
