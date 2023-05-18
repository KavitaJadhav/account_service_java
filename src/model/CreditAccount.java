package model;

import java.math.BigDecimal;

public class CreditAccount extends Account {
    public CreditAccount(long id, BigDecimal balance) {
        super(id, balance);
    }

    @Override
    public Account clone() {
        return new CreditAccount(this.id, this.balance);
    }
}
