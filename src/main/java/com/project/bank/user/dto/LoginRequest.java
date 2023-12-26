package com.project.bank.user.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public record LoginRequest(String email, String password) {

    public UsernamePasswordAuthenticationToken toAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
