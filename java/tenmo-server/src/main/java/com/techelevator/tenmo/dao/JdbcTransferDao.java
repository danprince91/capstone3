package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public BigDecimal getBalance(long id){
        BigDecimal balance = BigDecimal.ZERO;
        // get users current balance
        String sql = "SELECT balance FROM accounts WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);


        if (results.next()) {
            balance = results.getBigDecimal("balance");
            return balance;
        }
        else {
            return balance;
        }
    }


    //send transfer
    @Override
    public void transferMoney(BigDecimal money, long fromAccountId, long toAccountId) {
        //BigDecimal currentBalanceFrom = getBalance(fromId);



        String sql = "INSERT INTO transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "VALUES (2, 2, ?, ?, ?);";
        jdbcTemplate.update(sql, fromAccountId, toAccountId, money);

        withdrawMoney(money, fromAccountId);
        depositMoney(money, toAccountId);
    }

    @Override
    public void depositMoney (BigDecimal money, long accountId) {
        BigDecimal currentBalance = getBalance(accountId);
        String sql = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?;";
        jdbcTemplate.update(sql, money, accountId);
    }
    @Override
    public List<TransferDTO> getAllTransfersForAccountId(long accountId){
        List<TransferDTO> transfers = new ArrayList<>();

        String sql = "SELECT transfers.transfer_id, account_to, amount FROM transfers WHERE (transfers.account_to = ? OR transfers.account_from = ?);";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId, accountId);

        while (results.next()) {
            transfers.add(mapRowToTransferDTO(results));
        }
        return transfers;
    }

    @Override
    public Transfer getTransferDetailsForTransferId(long transferId){
        Transfer transfer = null;
        String sql = "SELECT * FROM transfers WHERE transfers.transfer_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferId);

        if (results.next()) {
            transfer = mapRowToTransfer(results);
        }
        return transfer;
    }

    @Override
    public void withdrawMoney(BigDecimal money, long accountId) {
        BigDecimal currentBalance = getBalance(accountId);
        String sql = "UPDATE accounts SET balance = balance - ? WHERE account_id = ?;";
        jdbcTemplate.update(sql, money, accountId);

    }

    private TransferDTO mapRowToTransferDTO(SqlRowSet rowSet) {
        TransferDTO transfer = new TransferDTO();
        transfer.setTransferId(rowSet.getLong("transfer_id"));
        transfer.setAccountTo(rowSet.getLong("account_to"));
        //to do, fix
        transfer.setAmount(rowSet.getBigDecimal("amount").doubleValue());

        return transfer;
    }

    private Transfer mapRowToTransfer(SqlRowSet rowSet) {
        Transfer transfer = new Transfer();
        transfer.setTransferId(rowSet.getLong("transfer_id"));
        transfer.setAccountTo(rowSet.getLong("account_to"));
        transfer.setAccountFrom(rowSet.getLong("account_from"));
        transfer.setTransferStatusId(rowSet.getLong("transfer_status_id"));
        transfer.setTransferTypeId(rowSet.getLong("transfer_type_id"));
        transfer.setAmount(rowSet.getLong("amount"));

        return transfer;

    }


}
