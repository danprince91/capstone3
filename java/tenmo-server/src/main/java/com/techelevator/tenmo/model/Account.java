package com.techelevator.tenmo.model;

public class Account {

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Account(){};

    public Account(Long accountId, Long userId, Long balance) {
        this.accountId = accountId;
        this.userId = userId;
        this.balance = balance;
    }

    private Long accountId;
    private Long userId;
    private Long balance;


}
