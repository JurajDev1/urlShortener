package com.urlshortener.application.repository;

import com.urlshortener.application.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public
interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByAccountId(String accountId);

    List<Account> findByAccountIdAndPassword(String accountId, String password);


}
