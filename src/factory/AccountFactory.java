package factory;

import model.Account;
import model.CreditAccount;
import model.SavingAccount;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import static model.Account.Type;

public class AccountFactory {

    public Account create(Map<String, String> accountDetails) throws IOException {

        String accountType = accountDetails.get("type");
        long accountId = Long.valueOf(accountDetails.get("account_id"));
        BigDecimal balance = new BigDecimal(accountDetails.get("opening_balance"));

        switch (accountType) {
            case "Credit":
                return new CreditAccount(accountId, balance);
            case "Saving":
                return new SavingAccount(accountId, balance);
            default:
                throw new IOException("Invalid Account type");
        }
    }

}
