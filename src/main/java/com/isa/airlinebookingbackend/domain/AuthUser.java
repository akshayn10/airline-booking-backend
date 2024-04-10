package com.isa.airlinebookingbackend.domain;


import com.isa.airlinebookingbackend.entity.auth.User;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static com.isa.airlinebookingbackend.constant.Constants.ROLE_PREFIX;


@Getter
@Slf4j
public class AuthUser extends User implements UserDetails {
    private final Collection<? extends GrantedAuthority> grantedAuthorities;
    private final AtomicReference<User> user = new AtomicReference<>();

    public AuthUser(User user) {
        this.user.set(user);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + user.getRole().toString()));
        log.info("authorities: {}", authorities);
        this.grantedAuthorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public String getUsername() {
        return user.get().getUsername();
    }

    @Override
    public String getPassword() {
        return user.get().getPassword();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.get().isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.get().isEnabled();
    }
}