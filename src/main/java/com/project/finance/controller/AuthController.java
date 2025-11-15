package com.project.finance.controller;

import com.project.finance.dto.*;
import com.project.finance.repository.UserRepo;
import com.project.finance.service.AuthService;
import com.project.finance.service.JwtService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@SecurityRequirement(name = " ")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/signup")
    public User registerUser(@RequestBody @Valid RegisterUserDto userDto){
        return authService.signUp(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> registerUser(@RequestBody @Valid LoginDto userDto){
        LoginResponse response = authService.authenticate(userDto);
        return ResponseEntity.ok(response);
    }

}
