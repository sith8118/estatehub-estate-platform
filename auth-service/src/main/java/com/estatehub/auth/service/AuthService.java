package com.estatehub.auth.service;

import com.estatehub.auth.dto.LoginRequest;
import com.estatehub.auth.dto.RegisterRequest;
import com.estatehub.auth.dto.AuthResponse;
import com.estatehub.auth.dto.UserProfileResponse;
import com.estatehub.auth.model.User;

public interface AuthService {
    User register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    UserProfileResponse getUserProfile(String email);
}
