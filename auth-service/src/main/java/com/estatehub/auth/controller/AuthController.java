package com.estatehub.auth.controller;

import com.estatehub.auth.dto.AuthResponse;
import com.estatehub.auth.dto.LoginRequest;
import com.estatehub.auth.dto.RegisterRequest;
import com.estatehub.auth.dto.UserProfileResponse;
import com.estatehub.auth.model.User;
import com.estatehub.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
        User user = authService.register(request);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> profile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserProfileResponse response = authService.getUserProfile(email);
        return ResponseEntity.ok(response);
    }
}