package com.project.bank.account.dto;

import java.util.List;

public record HistoryListResponse(List<HistoryResponse> histories) {
}
