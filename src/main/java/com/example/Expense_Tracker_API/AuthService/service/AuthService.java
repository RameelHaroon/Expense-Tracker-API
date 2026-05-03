package com.example.Expense_Tracker_API.AuthService.service;

import com.example.Expense_Tracker_API.AuthService.dto.AuthResponse;
import com.example.Expense_Tracker_API.AuthService.dto.LoginRequest;
import com.example.Expense_Tracker_API.AuthService.dto.RegisterRequest;
import com.example.Expense_Tracker_API.AuthService.entity.User;
import com.example.Expense_Tracker_API.AuthService.exception.EmailAlreadyExistsException;
import com.example.Expense_Tracker_API.AuthService.repository.UserRepository;
import com.example.Expense_Tracker_API.AuthService.security.CustomUserDetails;
import com.example.Expense_Tracker_API.AuthService.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);

        CustomUserDetails userDetails = new CustomUserDetails(user);
        String token = jwtUtils.generateToken(userDetails);

        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String token = jwtUtils.generateToken(userDetails);

        return new AuthResponse(token);
    }
}
