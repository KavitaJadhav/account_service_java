package service;

import model.Account;
import repository.AccountRepository;

public class SavingAccountService implements AccountService {
    private final AccountRepository repository;

    public SavingAccountService(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createAccount(Account account) {
        repository.create(account);
    }
}
