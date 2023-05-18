package service;

import model.Account;
import model.SavingAccount;
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

    @Override
    public SavingAccount getAccount(long id) {
        return (SavingAccount) repository.get(id);
    }
}
