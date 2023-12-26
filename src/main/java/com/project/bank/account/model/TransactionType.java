package com.project.bank.account.model;

import lombok.Getter;

@Getter
public enum TransactionType {
    DEPOSIT("입금", 1),
    WITH_DRAW("출금", -1),
    ADJUST("조정", 0);

    private final String value;
    private final int sign;

    TransactionType(String value, int sign) {
        this.value = value;
        this.sign = sign;
    }

}
