package com.isa.airlinebookingbackend.repository;

import com.isa.airlinebookingbackend.entity.auth.OTP;
import com.isa.airlinebookingbackend.entity.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OTPRepository extends JpaRepository<OTP, Long> {

    Optional<OTP> findByUser(User user);
    void deleteByUser(User user);


    
}