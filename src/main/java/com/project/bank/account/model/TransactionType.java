package com.project.bank.account.model;

public enum TransactionType {
    DEPOSIT("입금"),
    WITH_DRAW("출금"),
    ADJUST("조정");

    private final String value;

    TransactionType(String value) {
        this.value = value;
    }
}
