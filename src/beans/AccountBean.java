package beans;

import factory.AccountFactory;
import model.Account;
import model.SavingAccount;
import repository.AccountRepository;
import service.CreditAccountService;
import service.SavingAccountService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AccountBean {
    public static final String DELIMITER = " ";
    private final AccountRepository repository;

    public AccountBean(AccountRepository repository) {
        this.repository = repository;
    }

    public void load() {
        CreditAccountService creditAccountService = new CreditAccountService(repository);
        SavingAccountService savingAccountService = new SavingAccountService(repository);

        try {
            FileInputStream fis = new FileInputStream("src/beans/input.txt");
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
    }
}
