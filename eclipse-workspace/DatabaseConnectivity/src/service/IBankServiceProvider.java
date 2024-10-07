package service;


	import java.util.List;

import bean.Account;
import bean.Customer;

	public interface IBankServiceProvider {
	    void createAccount(Customer customer, long accNo, String accType, float balance);
	    List<Account> listAccounts();
	    float calculateInterest(long accountNumber);
		void create_account(Customer customer, long accNo, String accType, float balance);
		void calculateInterest();
	}


