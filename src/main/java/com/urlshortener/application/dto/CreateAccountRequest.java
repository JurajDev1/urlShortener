package com.urlshortener.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateAccountRequest {
    @JsonProperty("AccountId")
    public String accountId;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
