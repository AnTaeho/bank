package com.project.bank.user.dto;

import com.project.bank.user.model.User;

public record UserResponse(Long id) {

    public static UserResponse toResponse(User user) {
        return new UserResponse(user.getId());
    }
}
