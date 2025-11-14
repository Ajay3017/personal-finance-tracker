package com.project.finance.service.impl;

import com.project.finance.dto.User;
import com.project.finance.dto.UserDetailsImpl;
import com.project.finance.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepo.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with name: " + name));
        return new UserDetailsImpl(user);
    }
}
