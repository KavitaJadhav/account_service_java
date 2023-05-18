package service;

import model.Account;
import model.CreditAccount;
import repository.AccountRepository;

public class CreditAccountService implements AccountService {
    private final AccountRepository repository;

    public CreditAccountService(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createAccount(Account account) {
        repository.create(account);
    }
}
