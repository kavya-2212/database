package service;


	import java.util.HashMap;
	import java.util.Map;

import bean.Account;
import bean.Customer;

	public class CustomerServiceProviderImpl implements ICustomerServiceProvider {
	    private Map<Long, Account> accountMap = new HashMap<>(); // To store accounts in memory

	    // Implement the required methods
	    @Override
	    public float get_account_balance(long account_number) {
	        Account account = accountMap.get(account_number);
	        return account != null ? account.getBalance() : 0;
	    }

	    @Override
	    public float deposit(long account_number, float amount) {
	        Account account = accountMap.get(account_number);
	        if (account != null) {
	            account.setBalance(account.getBalance() + amount);
	            return account.getBalance();
	        }
	        return 0;
	    }

	    @Override
	    public float withdraw(long account_number, float amount) {
	        Account account = accountMap.get(account_number);
	        if (account != null) {
	            // Check minimum balance for Savings or overdraft limit for Current account (for simplicity, not included here)
	            if (account.getBalance() >= amount) {
	                account.setBalance(account.getBalance() - amount);
	                return account.getBalance();
	            } else {
	                System.out.println("Insufficient balance!");
	            }
	        }
	        return 0;
	    }

	    @Override
	    public void transfer(long from_account_number, long to_account_number, float amount) {
	        Account fromAccount = accountMap.get(from_account_number);
	        Account toAccount = accountMap.get(to_account_number);

	        if (fromAccount != null && toAccount != null && fromAccount.getBalance() >= amount) {
	            fromAccount.setBalance(fromAccount.getBalance() - amount);
	            toAccount.setBalance(toAccount.getBalance() + amount);
	            System.out.println("Transfer successful.");
	        } else {
	            System.out.println("Transfer failed.");
	        }
	    }

	    @Override
	    public Account getAccountDetails(long account_number) {
	        return accountMap.get(account_number);
	    }

	    // Method to create and add accounts to the accountMap for testing purposes
	    public void addAccount(Account account) {
	        accountMap.put(account.getAccountNumber(), account);
	    }

	    // Additional methods as required by the interface...
	
	 public static void main(String[] args) {
	        // Create the CustomerServiceProviderImpl object
	        CustomerServiceProviderImpl customerService = new CustomerServiceProviderImpl();

	        // Create some customers and accounts
	        Customer customer1 = new Customer(101, "Joen", "Bam", "joen.bam@gmail.com", "9876543210", "vizag");
	        Customer customer2 = new Customer(102, "", "nam", "rm.nam@gmail.com", "9876543211", "Rnb");

	        // Create accounts for the customers
	        Account account1 = new Account("Savings", 1000.0f, customer1);
	        Account account2 = new Account("Current", 2000.0f, customer2);

	        // Add accounts to the service for tracking
	        customerService.addAccount(account1);
	        customerService.addAccount(account2);

	        // Simulate banking operations
	        System.out.println("Initial balance for Account 1: " + customerService.get_account_balance(account1.getAccountNumber()));

	        // Deposit money into account1
	        customerService.deposit(account1.getAccountNumber(), 500.0f);
	        System.out.println("Balance after deposit into Account 1: " + customerService.get_account_balance(account1.getAccountNumber()));

	        // Withdraw money from account1
	        customerService.withdraw(account1.getAccountNumber(), 300.0f);
	        System.out.println("Balance after withdrawal from Account 1: " + customerService.get_account_balance(account1.getAccountNumber()));

	        // Transfer money from account2 to account1
	        customerService.transfer(account2.getAccountNumber(), account1.getAccountNumber(), 200.0f);
	        System.out.println("Balance for Account 1 after transfer: " + customerService.get_account_balance(account1.getAccountNumber()));
	        System.out.println("Balance for Account 2 after transfer: " + customerService.get_account_balance(account2.getAccountNumber()));
	        
	        // Display account details
	        Account details = customerService.getAccountDetails(account1.getAccountNumber());
	        System.out.println("Account Holder: " + details.getCustomer().getFirstName() + " " + details.getCustomer().getLastName());
	        System.out.println("Account Type: " + details.getAccountType());
	        System.out.println("Current Balance: " + details.getBalance());
	    }

		@Override
		public float getAccountBalance(long accountNumber) {
			// TODO Auto-generated method stub
			return 0;
		}
	}

