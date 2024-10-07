package service;

import bean.Account;
import bean.Customer;
	public interface ICustomerServiceProvider {
	    float getAccountBalance(long accountNumber);
	    float deposit(long accountNumber, float amount);
	    float withdraw(long accountNumber, float amount);
	    void transfer(long fromAccountNumber, long toAccountNumber, float amount);
	    Account getAccountDetails(long accountNumber);
		float get_account_balance(long account_number);
	}


