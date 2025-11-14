package com.project.finance.controller;

import com.project.finance.dto.*;
import com.project.finance.service.AuthService;
import com.project.finance.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
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
        User authenticatedUser = authService.authenticate(userDto);
        String jwt = jwtService.generateToken(new UserDetailsImpl(authenticatedUser));
        LoginResponse loginResponse = new LoginResponse(jwt, jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }

}
