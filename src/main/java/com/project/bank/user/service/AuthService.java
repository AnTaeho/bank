package com.project.bank.user.service;

import com.project.bank.user.dto.JoinRequest;
import com.project.bank.user.dto.UserResponse;
import com.project.bank.user.model.User;
import com.project.bank.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse join(JoinRequest joinRequest) {
        User user = new User(
                joinRequest.name(),
                joinRequest.email(),
                joinRequest.name()
        );
        user.encodePassword(passwordEncoder);
        return UserResponse.toResponse(userRepository.save(user));
    }
}
