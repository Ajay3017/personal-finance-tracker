package com.project.finance.service;

import com.project.finance.dto.*;
import com.project.finance.entity.UserEntity;
import com.project.finance.repository.UserRepo;
import com.project.finance.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserMapper userMapper;

    public User signUp(RegisterUserDto userdto){
        UserEntity entity = new UserEntity(
                userdto.getName(),
                userdto.getEmail(),
                passwordEncoder.encode(userdto.getPassword())
        );

        userRepo.save(entity);
        return userMapper.getUser(entity);
    }

    public LoginResponse authenticate(LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getName(),
                        loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepo.findByName(loginDto.getName())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(new UserDetailsImpl(user));
        return new LoginResponse(jwtToken, jwtService.getExpirationTime());
    }
}
