package bean;

	public class SavingsAccount extends Account {
	    private float interestRate;
	    private static final float MINIMUM_BALANCE = 500;

	    public SavingsAccount(float accountBalance, Customer customer, float interestRate) {
	        super("Savings", accountBalance >= MINIMUM_BALANCE ? accountBalance : MINIMUM_BALANCE, customer);
	        this.interestRate = interestRate;
	    }

	    public static void main(String[] args) {
	        Customer customer = new Customer(101, "Joen", "Bam", "joen.bam@gmail.com", "9876543210", "vizag");
	        SavingsAccount savingsAccount = new SavingsAccount(700.0f, customer, 4.0f);
	        System.out.println("Savings Account Details:");
	        System.out.println("Account Number: " + savingsAccount.getAccountNumber());
	        System.out.println("Account Balance: " + savingsAccount.getBalance());
	        System.out.println("Interest Rate: " + savingsAccount.interestRate);
	    }
	}


