package com.project.bank.account.service;

import com.project.bank.account.dto.BalanceResponse;
import com.project.bank.account.dto.TransactionRequest;
import com.project.bank.account.dto.TransactionResponse;
import com.project.bank.account.model.Account;
import com.project.bank.account.model.History;
import com.project.bank.account.repository.AccountRepository;
import com.project.bank.account.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final HistoryRepository historyRepository;

    @Transactional
    public TransactionResponse transaction(Long accountId, TransactionRequest request) {
        Account account = accountRepository.findById(accountId).orElseThrow(IllegalArgumentException::new);
        account.updateBalance(request.amount(), request.type());
        saveHistory(account, request);
        return new TransactionResponse(request.amount(), request.type().getValue());
    }

    private void saveHistory(Account account, TransactionRequest request) {
        History history = new History(request.amount(), request.type(), account);
        historyRepository.save(history);
    }

    public BalanceResponse getBalance(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(IllegalArgumentException::new);
        return new BalanceResponse(account.getBalance());
    }
}
