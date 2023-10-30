package com.example.springsecu.service;

import com.example.springsecu.model.User;
import com.example.springsecu.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByName(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                String.format("USER_NOT_FOUND: " + username)
                        )
                );
    }


    public void save(User user) {
        userRepo.save(user);
    }
}