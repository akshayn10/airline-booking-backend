package com.isa.airlinebookingbackend.repository;

import com.isa.airlinebookingbackend.entity.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
