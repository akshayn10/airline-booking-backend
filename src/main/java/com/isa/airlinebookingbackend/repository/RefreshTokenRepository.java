package com.isa.airlinebookingbackend.repository;

import com.isa.airlinebookingbackend.entity.auth.RefreshToken;
import com.isa.airlinebookingbackend.entity.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);


    Optional<RefreshToken> findByUser(User user);

    
}