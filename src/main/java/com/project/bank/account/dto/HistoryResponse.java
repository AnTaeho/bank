package com.project.bank.account.dto;

import com.project.bank.account.model.History;
import com.project.bank.account.model.TransactionType;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import lombok.Data;

@Data
public class HistoryResponse {

    private final Long id;
    private final int amount;
    private final TransactionType type;
    private final String dateFormat;

    public HistoryResponse(History history) {
        this.id = history.getId();
        this.amount = history.getAmount();
        this.type = history.getType();
        this.dateFormat = history.getLocalDateTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
    }
}
