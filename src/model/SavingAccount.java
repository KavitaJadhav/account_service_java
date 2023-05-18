package model;

import java.math.BigDecimal;

public class SavingAccount extends Account {
    public SavingAccount(long id, BigDecimal balance) {
        super(id, balance);
    }

    @Override
    public Account clone() {
        return new SavingAccount(this.id, this.balance);
    }

}
