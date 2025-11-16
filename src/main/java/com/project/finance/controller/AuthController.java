package com.project.finance.controller;

import com.project.finance.dto.*;
import com.project.finance.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public User registerUser(@RequestBody @Valid RegisterUserDto userDto){
        return authService.signUp(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> registerUser(@RequestBody @Valid LoginDto userDto){
        String token = authService.authenticate(userDto);
        return ResponseEntity.ok(token);
    }

}
