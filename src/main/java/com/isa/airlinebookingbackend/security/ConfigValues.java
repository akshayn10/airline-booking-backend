package com.isa.airlinebookingbackend.security;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
@Getter
public class ConfigValues {
    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.expiration.access-token}")
    private long accessExpiration;
    @Value("${jwt.expiration.refresh-token}")
    private long refreshExpiration;
    @Value("${otp.expiration}")
    private long otpExpiration;

}
