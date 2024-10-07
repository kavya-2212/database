package bean;
public class Account {
    private static long lastAccNo = 1000;
    private long accountNumber;
    private String accountType;
    private float balance;
    private Customer customer;


    public Account(String accountType, float balance, Customer customer) {
        this.accountNumber = ++lastAccNo;
        this.accountType = accountType;
        this.balance = balance;
        this.customer = customer;
    }

    // Getter method for accountNumber
    public long getAccountNumber() {
        return accountNumber;
    }

    // Other getters and setters
    public String getAccountType() {
        return accountType;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

	    

	    public static void main(String[] args) {
	        // Creating a customer object
	        Customer customer = new Customer(101, "Joen", "Bam", "joen.bam@gmail.com", "1234567890","vizag");

	        // Creating an account object
	        Account account = new Account("Savings", 1000.0f, customer);

	        // Displaying account details
	        System.out.println("Account Details:");
	        System.out.println("Account Number: " + account.accountNumber);
	        System.out.println("Account Type: " + account.accountType);
	        System.out.println("Account Balance: " + account.getBalance());
	        System.out.println("Customer: " + account.getCustomer().getFirstName() + " " + account.getCustomer().getLastName());
	    }

		
	}

