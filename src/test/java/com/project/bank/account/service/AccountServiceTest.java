package com.project.bank.account.service;

import static org.assertj.core.api.Assertions.*;

import com.project.bank.account.dto.BalanceResponse;
import com.project.bank.account.dto.HistoryListResponse;
import com.project.bank.account.dto.TransactionRequest;
import com.project.bank.account.dto.TransactionResponse;
import com.project.bank.account.model.Account;
import com.project.bank.account.model.TransactionType;
import com.project.bank.account.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
class AccountServiceTest {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;

    @Test
    void transaction() {
        //given
        Account account = accountRepository.save(new Account());
        Long accountId = account.getId();
        TransactionRequest request
                = new TransactionRequest(2000, TransactionType.DEPOSIT);

        //when
        TransactionResponse response = accountService.transaction(accountId, request);

        //then
        assertThat(response.amount()).isEqualTo(request.amount());
        assertThat(response.type()).isEqualTo(request.type().getValue());
    }

    @Test
    void getBalance() {
        //given
        Account account = accountRepository.save(new Account());
        Long accountId = account.getId();
        TransactionRequest request
                = new TransactionRequest(2000, TransactionType.DEPOSIT);
        accountService.transaction(accountId, request);

        //when
        BalanceResponse response = accountService.getBalance(accountId);

        //then
        assertThat(response.amount()).isEqualTo(request.amount());
    }

    @Test
    void getAllHistory() {
        //given
        int three = 3;
        Account account = accountRepository.save(new Account());
        Long accountId = account.getId();
        TransactionRequest request
                = new TransactionRequest(2000, TransactionType.DEPOSIT);
        for (int i = 0; i <three; i++) {
            accountService.transaction(accountId, request);
        }

        //when
        HistoryListResponse response = accountService.getAllHistory(accountId);

        //then
        assertThat(response.histories().size()).isEqualTo(three);
    }
}