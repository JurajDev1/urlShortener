package com.urlshortener.application.dto;

public class CreateAccountResponse {
    public boolean success = false;
    public String description = "Non-successful registration because provided AccountID already exists.";

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
