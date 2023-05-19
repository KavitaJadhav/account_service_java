import model.Account;
import model.CreditAccount;
import model.SavingAccount;
import repository.AccountRepository;
import service.CreditAccountService;
import service.SavingAccountService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AccountRepository repository = new AccountRepository();
        CreditAccountService creditAccountService = new CreditAccountService(repository);
        SavingAccountService savingAccountService = new SavingAccountService(repository);

        List<Account> accounts = Arrays.asList(
                new CreditAccount(12341l, new BigDecimal("0.0")),
                new CreditAccount(12342l, new BigDecimal("0.0")),
                new SavingAccount(12343l, new BigDecimal("0.0")),
                new SavingAccount(12344l, new BigDecimal("0.0")),
                new SavingAccount(12345l, new BigDecimal("0.0"))
        );

        for (Account account : accounts) {
            if (account instanceof SavingAccount)
                savingAccountService.createAccount(account);
            else
                creditAccountService.createAccount(account);
        }

        SavingAccount savingAccount = savingAccountService.getAccount(12345l);
        savingAccountService.deposit(savingAccount.getId(), new BigDecimal("1000"));
        savingAccountService.withdraw(savingAccount.getId(), new BigDecimal("100"));
        savingAccount = savingAccountService.getAccount(12345l);
        System.out.println("balance in savingAccount " + savingAccount.getId() + " : " + savingAccount.getBalance());

        CreditAccount creditAccount = creditAccountService.getAccount(12341l);
        creditAccountService.withdraw(creditAccount.getId(), new BigDecimal("1000"));
        creditAccountService.deposit(creditAccount.getId(), new BigDecimal("500"));
        creditAccount = creditAccountService.getAccount(12341l);
        System.out.println("balance in creditAccount " + creditAccount.getId() + " : " + creditAccount.getBalance());

        System.out.println("Check account Id valid : 12343l " + savingAccountService.isValidAccountId(12343l));
        savingAccountService.delete(12343l);
        System.out.println("Check account Id valid : 12343l " + savingAccountService.isValidAccountId(12343l));

        System.out.println("Check account Id valid : 12342l " + creditAccountService.isValidAccountId(12342l));
        creditAccountService.delete(12342l);
        System.out.println("Check account Id valid : 12342l " + creditAccountService.isValidAccountId(12342l));
    }
}