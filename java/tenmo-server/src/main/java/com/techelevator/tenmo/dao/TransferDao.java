package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDTO;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {



    void transferMoney(BigDecimal money, long fromID, long toId);

    List<TransferDTO> getAllTransfersForAccountId(long accountId);

    void withdrawMoney (BigDecimal money, long id);

    void depositMoney(BigDecimal money, long id);

    BigDecimal getBalance(long id);

    Transfer getTransferDetailsForTransferId(long id);




}
