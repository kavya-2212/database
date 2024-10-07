
	package app;

	import service.BankRepositoryImpl;

import java.time.LocalDateTime;
	import java.util.List;
	import java.util.Scanner;

import bean.Account;
import bean.Customer;
import bean.Transaction;

	public class BankApp {
	    private static final BankRepositoryImpl bankRepository = new BankRepositoryImpl();
	    private static final Scanner scanner = new Scanner(System.in);

	    public static void main(String[] args) {
	        while (true) {
	            System.out.println("\n--- Banking System Menu ---");
	            System.out.println("1. Create Account");
	            System.out.println("2. Deposit");
	            System.out.println("3. Withdraw");
	            System.out.println("4. Get Balance");
	            System.out.println("5. Transfer");
	            System.out.println("6. Get Account Details");
	            System.out.println("7. List Accounts");
	            System.out.println("8. Get Transactions");
	            System.out.println("9. Exit");
	            System.out.print("Choose an option: ");
	            int choice = scanner.nextInt();

	            switch (choice) {
	                case 1:
	                    createAccount();
	                    break;
	                case 2:
	                    deposit();
	                    break;
	                case 3:
	                    withdraw();
	                    break;
	                case 4:
	                    getBalance();
	                    break;
	                case 5:
	                    transfer();
	                    break;
	                case 6:
	                    getAccountDetails();
	                    break;
	                case 7:
	                    listAccounts();
	                    break;
	                case 8:
	                    getTransactions();
	                    break;
	                case 9:
	                    System.out.println("Exiting the banking system. Goodbye!");
	                    return;
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	            }
	        }
	    }

	    private static void createAccount() {
	        System.out.print("Enter Customer ID: ");
	        long customerId = scanner.nextLong();
	        scanner.nextLine(); // consume the newline
	        System.out.print("Enter First Name: ");
	        String firstName = scanner.nextLine();
	        System.out.print("Enter Last Name: ");
	        String lastName = scanner.nextLine();
	        System.out.print("Enter Email Address: ");
	        String email = scanner.nextLine();
	        System.out.print("Enter Phone Number: ");
	        String phone = scanner.nextLine();
	        System.out.print("Enter Address: ");
	        String address = scanner.nextLine();

	        Customer customer = new Customer(customerId, firstName, lastName, email, phone, address);

	        System.out.println("\n--- Choose Account Type ---");
	        System.out.println("1. Savings Account");
	        System.out.println("2. Current Account");
	        System.out.println("3. Zero Balance Account");
	        System.out.print("Choose account type: ");
	        int accountTypeChoice = scanner.nextInt();

	        float initialBalance = 0;
	        if (accountTypeChoice == 1) {
	            System.out.print("Enter initial balance (Minimum 500): ");
	            initialBalance = scanner.nextFloat();
	            while (initialBalance < 500) {
	                System.out.print("Minimum balance for Savings Account is 500. Please enter again: ");
	                initialBalance = scanner.nextFloat();
	            }
	            bankRepository.createAccount(customer, 0, "Savings", initialBalance);
	        } else if (accountTypeChoice == 2) {
	            System.out.print("Enter initial balance: ");
	            initialBalance = scanner.nextFloat();
	            bankRepository.createAccount(customer, 0, "Current", initialBalance);
	        } else if (accountTypeChoice == 3) {
	            bankRepository.createAccount(customer, 0, "Zero Balance", 0);
	        } else {
	            System.out.println("Invalid account type.");
	            return;
	        }

	        System.out.println("Account created successfully.");
	    }

	    private static void deposit() {
	        System.out.print("Enter Account Number: ");
	        long accountNumber = scanner.nextLong();
	        System.out.print("Enter Amount to Deposit: ");
	        float amount = scanner.nextFloat();
	        bankRepository.deposit(accountNumber, amount);
	    }

	    private static void withdraw() {
	        System.out.print("Enter Account Number: ");
	        long accountNumber = scanner.nextLong();
	        System.out.print("Enter Amount to Withdraw: ");
	        float amount = scanner.nextFloat();
	        bankRepository.withdraw(accountNumber, amount);
	    }

	    private static void getBalance() {
	        System.out.print("Enter Account Number: ");
	        long accountNumber = scanner.nextLong();
	        float balance = bankRepository.getAccountBalance(accountNumber);
	        System.out.println("Current Balance: " + balance);
	    }

	    private static void transfer() {
	        System.out.print("Enter From Account Number: ");
	        long fromAccount = scanner.nextLong();
	        System.out.print("Enter To Account Number: ");
	        long toAccount = scanner.nextLong();
	        System.out.print("Enter Amount to Transfer: ");
	        float amount = scanner.nextFloat();
	        bankRepository.transfer(fromAccount, toAccount, amount);
	    }

	    private static void getAccountDetails() {
	        System.out.print("Enter Account Number: ");
	        long accountNumber = scanner.nextLong();
	        Account account = bankRepository.getAccountDetails(accountNumber);
	        if (account != null) {
	            System.out.println("Account Number: " + account.getAccountNumber());
	            System.out.println("Account Type: " + account.getAccountType());
	            System.out.println("Balance: " + account.getBalance());
	            System.out.println("Customer: " + account.getCustomer().getFirstName() + " " + account.getCustomer().getLastName());
	        } else {
	            System.out.println("Account not found.");
	        }
	    }

	    private static void listAccounts() {
	        List<Account> accounts = bankRepository.listAccounts();
	        System.out.println("\nAccounts in the Bank:");
	        for (Account account : accounts) {
	            System.out.println("Account Number: " + account.getAccountNumber() +
	                               ", Account Type: " + account.getAccountType() +
	                               ", Balance: " + account.getBalance() +
	                               ", Customer: " + account.getCustomer().getFirstName() + " " + account.getCustomer().getLastName());
	        }
	    }

	    private static void getTransactions() {
	        System.out.print("Enter Account Number: ");
	        long accountNumber = scanner.nextLong();
	        System.out.print("Enter From Date (yyyy-MM-dd HH:mm): ");
	        LocalDateTime fromDate = LocalDateTime.parse(scanner.next() + " " + scanner.next());
	        System.out.print("Enter To Date (yyyy-MM-dd HH:mm): ");
	        LocalDateTime toDate = LocalDateTime.parse(scanner.next() + " " + scanner.next());
	        
	        List<Transaction> transactions = bankRepository.getTransactions(accountNumber, fromDate, toDate);
	        System.out.println("\nTransactions for Account Number " + accountNumber + ":");
	        for (Transaction transaction : transactions) {
	            System.out.println("Transaction Type: " + transaction.getTransactionType() +
	                               ", Amount: " + transaction.getTransactionAmount() +
	                               ", Date: " + transaction.getDateTime());
	        }
	    }
	}


