package com.project.bank.account.controller;

import com.project.bank.account.dto.BalanceResponse;
import com.project.bank.account.dto.HistoryListResponse;
import com.project.bank.account.dto.TransactionRequest;
import com.project.bank.account.dto.TransactionResponse;
import com.project.bank.account.service.AccountService;
import com.project.bank.common.dto.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/{accountId}/transaction")
    public CommonResponse<TransactionResponse> transaction(@PathVariable(name = "accountId") Long accountId, @RequestBody TransactionRequest transactionRequest) {
        return new CommonResponse<>(accountService.transaction(accountId, transactionRequest));
    }

    @GetMapping("/{accountId}")
    public CommonResponse<BalanceResponse> getBalance(@PathVariable(name = "accountId") Long accountId) {
        return new CommonResponse<>(accountService.getBalance(accountId));
    }

    @GetMapping("/{accountId}/histories")
    public CommonResponse<HistoryListResponse> getAllHistory(@PathVariable(name = "accountId") Long accountId) {
        return new CommonResponse<>(accountService.getAllHistory(accountId));
    }
}
