package com.project.bank.user.dto;

public record JoinRequest(String name,
                          String email,
                          String password) {
}