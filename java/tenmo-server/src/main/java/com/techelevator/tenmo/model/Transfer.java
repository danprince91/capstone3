package com.techelevator.tenmo.model;

public class Transfer {

    private long transferId;
    private long transferTypeId;
    private long transferStatusId;
    private long accountFrom;
    private long accountTo;
    private double amount;

    public Transfer(long transferId, long transfer_type_id, long transfer_status_id, long account_from, long account_to, double amount) {
        this.transferId = transferId;
        this.transferTypeId = transfer_type_id;
        this.transferStatusId = transfer_status_id;
        this.accountFrom = account_from;
        this.accountTo = account_to;
        this.amount = amount;
    }

    public Transfer() {

    }

    public void setTransferId(long transferId) {
        this.transferId = transferId;
    }

    public void setTransferTypeId(long transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public void setTransferStatusId(long transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public void setAccountFrom(long accountFrom) {
        this.accountFrom = accountFrom;
    }

    public void setAccountTo(long accountTo) {
        this.accountTo = accountTo;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    public long getTransferId() {
        return transferId;
    }

    public long getTransferTypeId() {
        return transferTypeId;
    }

    public long getTransferStatusId() {
        return transferStatusId;
    }

    public long getAccountFrom() {
        return accountFrom;
    }

    public long getAccountTo() {
        return accountTo;
    }

    public double getAmount() {
        return amount;
    }



}
