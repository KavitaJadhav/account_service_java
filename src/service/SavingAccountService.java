package service;

import model.Account;
import model.SavingAccount;
import repository.AccountRepository;

import java.math.BigDecimal;

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

    @Override
    public void deposit(long id, BigDecimal amount) {
        Account account = repository.get(id);
        account.setBalance(account.getBalance().add(amount));
        repository.update(account);
    }

    @Override
    public void withdraw(long id, BigDecimal amount) {
        Account account = repository.get(id);
        account.setBalance(account.getBalance().subtract(amount));
        repository.update(account);
    }
}
