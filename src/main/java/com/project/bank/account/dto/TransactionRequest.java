package com.project.bank.account.dto;

import com.project.bank.account.model.TransactionType;

public record TransactionRequest(int amount, TransactionType type) {
}
