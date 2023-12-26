package com.project.bank.user.service;

import com.project.bank.common.jwt.service.JwtService;
import com.project.bank.user.dto.JoinRequest;
import com.project.bank.user.dto.TokenResponse;
import com.project.bank.user.dto.UserResponse;
import com.project.bank.user.model.User;
import com.project.bank.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Transactional
    public UserResponse join(JoinRequest joinRequest) {
        User user = new User(
                joinRequest.name(),
                joinRequest.email(),
                joinRequest.password()
        );
        user.encodePassword(passwordEncoder);
        return UserResponse.toResponse(userRepository.save(user));
    }

    @Transactional
    public TokenResponse login(UsernamePasswordAuthenticationToken loginRequest) {
        Authentication authentication = authenticationManager.authenticate(loginRequest);
        String email = authentication.getName();
        return jwtService.toTokenResponse(email);
    }
}
