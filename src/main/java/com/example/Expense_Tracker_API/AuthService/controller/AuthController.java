package com.example.Expense_Tracker_API.AuthService.controller;

import com.example.Expense_Tracker_API.AuthService.dto.AuthResponse;
import com.example.Expense_Tracker_API.AuthService.dto.LoginRequest;
import com.example.Expense_Tracker_API.AuthService.dto.RegisterRequest;
import com.example.Expense_Tracker_API.AuthService.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AuthController.BASE_URL)
public class AuthController {
    public static final String BASE_URL = "/api/authService";
    public static final String V1_AUTH_REGISTER = "/v1/auth/register";
    public static final String V1_AUTH_LOGIN = "/v1/auth/login";

    private final AuthService authService;

    @PostMapping(V1_AUTH_REGISTER)
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping(V1_AUTH_LOGIN)
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
