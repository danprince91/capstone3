package com.techelevator.tenmo.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class TransferDTO {

    private long transferId;

    @Positive
    private long accountTo;

    public long getTransferId() {
        return transferId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Positive
    private double amount;

    @Override
    public String toString() {
        return "TransferDTO{" +
                "transfer_id='"+transferId+'\'' +
                ", account_to='" + accountTo + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }

    public TransferDTO(long transferId, long accountTo, double amount) {
        this.transferId = transferId;
        this.accountTo = accountTo;
        this.amount = amount;
    }


    public TransferDTO() {

    }


    public void setTransferId(long transferId) {
        this.transferId = transferId;
    }

    public long getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(long accountTo) {
        this.accountTo = accountTo;
    }

    public double getAmount() {
        return amount;
    }








}
