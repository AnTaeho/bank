package com.project.bank.user.controller;

import com.project.bank.common.dto.CommonResponse;
import com.project.bank.user.dto.JoinRequest;
import com.project.bank.user.dto.LoginRequest;
import com.project.bank.user.dto.TokenResponse;
import com.project.bank.user.dto.UserResponse;
import com.project.bank.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/join")
    public CommonResponse<UserResponse> join(@RequestBody JoinRequest joinRequest) {
        return new CommonResponse<>(authService.join(joinRequest));
    }

    @PostMapping("/login")
    public CommonResponse<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        return new CommonResponse<>(authService.login(loginRequest));
    }
}
