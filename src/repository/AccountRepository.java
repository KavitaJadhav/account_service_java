package repository;

import model.Account;

import java.util.HashMap;
import java.util.Map;

public class AccountRepository {
    private Map<Long, Account> accounts = new HashMap();

    public void create(Account account) {
        this.accounts.put(account.getId(), account.clone());
    }

    public Account get(long id) {
        return this.accounts.get(id).clone();
    }

    public void update(Account account) {
        this.accounts.put(account.getId(), account.clone());
    }
}
