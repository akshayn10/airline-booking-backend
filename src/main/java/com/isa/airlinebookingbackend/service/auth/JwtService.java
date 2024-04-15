package com.isa.airlinebookingbackend.service.auth;

import java.util.Map;

public interface JwtService {

    public String extractEmail(String token);
    public boolean validateToken(String token);
    public String generateToken(String email, String role);


}
