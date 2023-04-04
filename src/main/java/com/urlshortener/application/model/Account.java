package com.urlshortener.application.model;

import jakarta.persistence.*;

@Entity
@Table(name = "account")
public class Account {

    private long id;
    @Column(name = "accountId", nullable = false)
    private String accountId;
    @Column(name = "password", nullable = false)
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
