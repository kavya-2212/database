package service;

import java.time.LocalDateTime;
import java.util.List;

import bean.Account;
import bean.Customer;
import bean.Transaction;

public interface IBankRepository {
    void createAccount(Customer customer, long accNo, String accType, float balance);
    List<Account> listAccounts();
    void calculateInterest();
    float getAccountBalance(long account_number);
    void deposit(long account_number, float amount); // Check this line
    void withdraw(long account_number, float amount);
    void transfer(long from_account_number, long to_account_number, float amount);
    Account getAccountDetails(long account_number);
    List<Transaction> getTransactions(long account_number, LocalDateTime fromDate, LocalDateTime toDate);
}
