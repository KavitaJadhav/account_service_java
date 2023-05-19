import beans.AccountBean;
import factory.AccountFactory;
import model.Account;
import model.CreditAccount;
import model.SavingAccount;
import repository.AccountRepository;
import service.CreditAccountService;
import service.SavingAccountService;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class Main {
    public static final String DELIMITER = " ";
    public static void main(String[] args) {

        AccountRepository repository = new AccountRepository();
        CreditAccountService creditAccountService = new CreditAccountService(repository);
        SavingAccountService savingAccountService = new SavingAccountService(repository);

        try {
            FileInputStream fis = new FileInputStream("src/input.txt");
            Scanner scanner = new Scanner(fis);
            String[] headers = scanner.nextLine().split(DELIMITER);

            String[] accountInput;
            Map<String, String> accountDetails = new HashMap<>();
            while (scanner.hasNext()) {
                accountInput = scanner.nextLine().split(DELIMITER);

                for (int i = 0; i < headers.length; i++) {
                    accountDetails.put(headers[i], accountInput[i]);
                }

                Account account = new AccountFactory().create(accountDetails);
                if (account instanceof SavingAccount)
                    savingAccountService.createAccount(account);
                else
                    creditAccountService.createAccount(account);

            }

        } catch (IOException exception) {
            System.out.println(exception.getMessage());
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