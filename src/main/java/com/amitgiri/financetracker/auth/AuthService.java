package com.amitgiri.financetracker.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.amitgiri.financetracker.user.User;
import com.amitgiri.financetracker.user.UserRepository;
import com.amitgiri.financetracker.user.UserStatus;
import com.amitgiri.financetracker.auth.config.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String register(User user) {

        if (userRepo.findByEmailIgnoreCase(user.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        user.setPassword(encoder.encode(user.getPassword()));

        // 🔥 IMPORTANT (your entity needs this)
        user.setStatus(UserStatus.ACTIVE);
        user.setTimezone("Asia/Kolkata");

        userRepo.save(user);

        return "User registered successfully";
    }

    public String login(String email, String password) {
    	System.out.println(email+" "+password);
        User user = userRepo.findByEmailIgnoreCase(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}