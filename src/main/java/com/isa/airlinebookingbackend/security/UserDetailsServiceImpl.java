package com.isa.airlinebookingbackend.security;

import com.isa.airlinebookingbackend.domain.AuthUser;
import com.isa.airlinebookingbackend.exception.auth.UserNotFoundException;
import com.isa.airlinebookingbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)  {
        log.info("Loading user by email: {}", email);
        var user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found for Email: " + email));
        if(!user.isEnabled()){
            throw new DisabledException("Email is not Activated");
        }
        if(!user.isAccountNonLocked()){
            throw new LockedException("User account is locked");
        }
        return new AuthUser(user);
    }
}
