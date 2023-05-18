package service;

import model.Account;
import model.CreditAccount;

public interface AccountService {
    void createAccount(Account account);

    Account getAccount(long id);
}
