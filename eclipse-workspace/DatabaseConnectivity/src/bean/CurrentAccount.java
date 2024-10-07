package bean;


	public class CurrentAccount extends Account {
	    private float overdraftLimit;

	    public CurrentAccount(float accountBalance, Customer customer, float overdraftLimit) {
	        super("Current", accountBalance, customer);
	        this.overdraftLimit = overdraftLimit;
	    }

	    public static void main(String[] args) {
	        // Creating a customer object
	        Customer customer = new Customer(102, "Alice", "Smith", "alice.smith@example.com", "1122334455", "789 Pine St");

	        // Creating a current account object
	        CurrentAccount currentAccount = new CurrentAccount(5000.0f, customer, 2000.0f);

	        // Displaying account details
	        System.out.println("Current Account Details:");
	        System.out.println("Account Number: " + currentAccount.getAccountNumber());
	        System.out.println("Account Balance: " + currentAccount.getBalance());
	        System.out.println("Overdraft Limit: " + currentAccount.overdraftLimit);
	    }
	}
