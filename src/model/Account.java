package model;

import java.math.BigDecimal;

public abstract class Account {
    public enum Type {
        CREDIT("Credit"),
        SAVING("Saving");

        private String name;

        Type(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    protected long id;
    protected BigDecimal balance;

    public Account(long id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public abstract Account clone();
}
