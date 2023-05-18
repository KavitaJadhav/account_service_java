package service;

import model.Account;

import java.math.BigDecimal;

public interface AccountService {
    void createAccount(Account account);

    Account getAccount(long id);

    void deposit(long id, BigDecimal amount);

    void withdraw(long id, BigDecimal amount);
}
