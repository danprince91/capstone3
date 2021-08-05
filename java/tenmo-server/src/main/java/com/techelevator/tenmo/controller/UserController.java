package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDTO;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class UserController {

    private UserDao userDao;
    private TransferDao transferDao;

    public UserController(UserDao userDao, TransferDao transferDao) {
        this.userDao = userDao;
        this.transferDao = transferDao;
    }



    @RequestMapping(value = "/balance", method = RequestMethod.GET)
    public BigDecimal getBalance( Principal principal) {
        String name = principal.getName();
        User user = userDao.findByUsername(name);
        long id = user.getId();

        return transferDao.getBalance(id);


    }

   /* @RequestMapping(value = "/balance", method = RequestMethod.PUT)
    public void withdrawMoney(Principal principal, BigDecimal money) {
        String name = principal.getName();
        User user = userDao.findByUsername(name);
        long id = user.getId();

        transferDao.withdrawMoney(money, id);

    }

    @RequestMapping(value = "/balance", method = RequestMethod.PUT)
    public void depositMoney(Principal principal, BigDecimal money) {
        String name = principal.getName();
        User user = userDao.findByUsername(name);
        long id = user.getId();

        transferDao.depositMoney(money, id);

    }*/

    @RequestMapping(path = "/balance", method = RequestMethod.PUT)
    public void transferMoney(Principal principal, @Valid @RequestBody TransferDTO transferDto) {
        String name = principal.getName();
        User userFrom = userDao.findByUsername(name);
        int userIdFrom = Math.toIntExact(userFrom.getId());
        int accountIdFrom = userDao.findAccountIdByUserId(userIdFrom);

        long idTo = transferDto.getAccountTo();
        BigDecimal amount = new BigDecimal(transferDto.getAmount());

        transferDao.transferMoney(amount, accountIdFrom, idTo);



    }

    @RequestMapping(path="/users", method = RequestMethod.GET)
    public List<User> list() {
        return userDao.findAll();
    }

    @RequestMapping(path="/transfers_list", method = RequestMethod.GET)
    public List<TransferDTO> listAllTransfersForUser(Principal principal) {
        String name = principal.getName();
        User user = userDao.findByUsername(name);
        int userId = Math.toIntExact(user.getId());
        int accountId = userDao.findAccountIdByUserId(userId);

        return transferDao.getAllTransfersForAccountId(accountId);
    }

    @RequestMapping(path="transfer_details", method = RequestMethod.GET)
    public Transfer listAllTransferDetailsForTransferId(Principal principal, @Valid @RequestParam long transferId) {
        String name = principal.getName();
        User user = userDao.findByUsername(name);
        int userId = Math.toIntExact(user.getId());

        return transferDao.getTransferDetailsForTransferId(transferId);
    }


}
