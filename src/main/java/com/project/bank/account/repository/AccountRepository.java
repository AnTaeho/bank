package com.project.bank.account.repository;

import com.project.bank.account.model.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @EntityGraph(attributePaths = "histories")
    @Query("select a from Account a where a.id = :accountId")
    Optional<Account> findByIdWithHistory(@Param(value = "accountId") Long accountId);

}
