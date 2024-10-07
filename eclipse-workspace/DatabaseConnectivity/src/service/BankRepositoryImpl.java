package service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Account;
import bean.Customer;
import bean.Transaction;

public class BankRepositoryImpl implements IBankRepository {
    private final Map<Long, Account> accountsDatabase = new HashMap<>();
    private final List<Transaction> transactionsDatabase = new ArrayList<>();

    @Override
    public void createAccount(Customer customer, long accNo, String accType, float balance) {
        Account newAccount = new Account(accType, balance, customer);
        accountsDatabase.put(newAccount.getAccountNumber(), newAccount);
        System.out.println("Account created in database for customer: " + customer.getFirstName() + " " + customer.getLastName());
    }

    @Override
    public List<Account> listAccounts() {
        return new ArrayList<>(accountsDatabase.values());
    }

    @Override
    public void calculateInterest() {
        for (Account account : accountsDatabase.values()) {
            if (account.getAccountType().equalsIgnoreCase("Savings")) {
                float interest = account.getBalance() * 0.05f; // Assuming 4% interest
                account.setBalance(account.getBalance() + interest);
            }
        }
    }

    @Override
    public float getAccountBalance(long account_number) {
        Account account = accountsDatabase.get(account_number);
        if (account != null) {
            return account.getBalance();
        }
        System.out.println("Account not found!");
        return 0;
    }

    @Override
    public void deposit(long account_number, float amount) {
        Account account = accountsDatabase.get(account_number);
        if (account != null) {
            account.setBalance(account.getBalance() + amount);
            Transaction transaction = new Transaction(account, "Deposit", "Deposit", amount);
            transactionsDatabase.add(transaction);
            System.out.println("Deposit successful. New balance: " + account.getBalance());
        } else {
            System.out.println("Account not found for deposit!");
        }
    }

    @Override
    public void withdraw(long account_number, float amount) {
        Account account = accountsDatabase.get(account_number);
        if (account != null) {
            // Check for minimum balance for savings account
            if (account.getAccountType().equalsIgnoreCase("Savings") && (account.getBalance() - amount < 500)) {
                System.out.println("Withdrawal violates minimum balance requirement!");
            } else {
                account.setBalance(account.getBalance() - amount);
                Transaction transaction = new Transaction(account, "Withdrawal", "Withdraw", amount);
                transactionsDatabase.add(transaction);
                System.out.println("Withdrawal successful. New balance: " + account.getBalance());
            }
        } else {
            System.out.println("Account not found for withdrawal!");
        }
    }

    @Override
    public void transfer(long from_account_number, long to_account_number, float amount) {
        Account fromAccount = accountsDatabase.get(from_account_number);
        Account toAccount = accountsDatabase.get(to_account_number);

        if (fromAccount != null && toAccount != null) {
            withdraw(from_account_number, amount); // Withdraw from source account
            deposit(to_account_number, amount); // Deposit into destination account
            System.out.println("Transfer successful from Account " + from_account_number + " to Account " + to_account_number);
        } else {
            System.out.println("One or both accounts not found!");
        }
    }

    @Override
    public Account getAccountDetails(long account_number) {
        return accountsDatabase.get(account_number);
    }

    @Override
    public List<Transaction> getTransactions(long account_number, LocalDateTime fromDate, LocalDateTime toDate) {
        List<Transaction> filteredTransactions = new ArrayList<>();
        for (Transaction transaction : transactionsDatabase) {
            if (transaction.getAccount().getAccountNumber() == account_number &&
                (transaction.getDateTime().isAfter(fromDate) || transaction.getDateTime().isEqual(fromDate)) &&
                (transaction.getDateTime().isBefore(toDate) || transaction.getDateTime().isEqual(toDate))) {
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

    // Main method for testing
    public static void main(String[] args) {
        BankRepositoryImpl bankRepo = new BankRepositoryImpl();

        // Creating sample customers
        Customer customer1 = new Customer(101, "Joen", "Bam", "joen.bam@gmail.com", "9876543210", "Vizag");
        Customer customer2 = new Customer(102, "rm", "Nam", "rm.nam@gmail.com", "1234567890", "Rnb");

        // Creating accounts
        bankRepo.createAccount(customer1, 1001, "Savings", 10000);
        bankRepo.createAccount(customer2, 1002, "Current", 15000);

        // Listing accounts
        System.out.println("\nAccounts in the database:");
        for (Account account : bankRepo.listAccounts()) {
            System.out.println("Account Number: " + account.getAccountNumber() + 
                               ", Account Type: " + account.getAccountType() + 
                               ", Balance: " + account.getBalance() + 
                               ", Customer: " + account.getCustomer().getFirstName() + " " + account.getCustomer().getLastName());
        }

        // Performing transactions
        bankRepo.deposit(1001, 1500); // Depositing into first account
        bankRepo.withdraw(1001, 1000); // Withdrawing from first account
        bankRepo.transfer(1001, 1002, 1200); // Transferring from first account to second account
        
        // Getting account details
        Account accountDetails = bankRepo.getAccountDetails(1001);
        System.out.println("\nAccount Details:");
        if (accountDetails != null) {
            System.out.println("Account Number: " + accountDetails.getAccountNumber() + 
                               ", Account Type: " + accountDetails.getAccountType() + 
                               ", Balance: " + accountDetails.getBalance());
        }

        // Retrieving transactions for account
        LocalDateTime fromDate = LocalDateTime.now().minusDays(20); // 30 days ago
        LocalDateTime toDate = LocalDateTime.now(); // current date
        List<Transaction> transactions = bankRepo.getTransactions(1001, fromDate, toDate);
        
        System.out.println("\nTransactions for Account Number 1001:");
        for (Transaction transaction : transactions) {
            System.out.println("Transaction Type: " + transaction.getTransactionType() + 
                               ", Amount: " + transaction.getTransactionAmount() + 
                               ", Date: " + transaction.getDateTime());
        }
    }
}
