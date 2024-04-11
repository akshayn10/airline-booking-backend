package com.isa.airlinebookingbackend.controller;

import com.isa.airlinebookingbackend.service.auth.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

}
