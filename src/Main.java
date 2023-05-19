import beans.AccountBean;
import model.CreditAccount;
import model.SavingAccount;
import repository.AccountRepository;
import service.CreditAccountService;
import service.SavingAccountService;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        AccountRepository repository = new AccountRepository();
        CreditAccountService creditAccountService = new CreditAccountService(repository);
        SavingAccountService savingAccountService = new SavingAccountService(repository);

        new AccountBean(repository).load();

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