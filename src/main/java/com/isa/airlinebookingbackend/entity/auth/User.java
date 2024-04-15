package com.isa.airlinebookingbackend.entity.auth;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotEmpty
    @NotBlank(message = "Email cannot be empty")
    @Column(name = "email", unique = true)
    private String email;
    private String firstName;
    private String lastName;
    @Column(name = "user_name", unique = true)
    @NotEmpty
    @NotBlank(message = "Username cannot be empty")
    @NotEmpty
    @NotBlank(message = "Password cannot be empty")
    private String username;
    private String password;
    private String addressLine;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;
    private boolean isEnabled;
    private boolean isAccountNonLocked;
    private String phoneNumberPrefix;
    private String phoneNumber;
}
