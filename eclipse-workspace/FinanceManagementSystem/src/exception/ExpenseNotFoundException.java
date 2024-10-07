package exception;


	// Custom Exception Class
	public class ExpenseNotFoundException extends Exception {
	    public ExpenseNotFoundException(String message) {
	        super(message);
	    }

	    // Main method for testing the exception
	    public static void main(String[] args) {
	        try {
	            // Simulate a scenario where the expense is not found
	            int expenseId = 100;  // Example expenseId that doesn't exist
	            boolean expenseExists = checkExpenseInDatabase(expenseId);

	            if (!expenseExists) {
	                throw new ExpenseNotFoundException("Expense with ID " + expenseId + " not found.");
	            }

	        } catch (ExpenseNotFoundException e) {
	            System.out.println(e.getMessage());
	        }
	    }

	    // Simulate database expense lookup (returns false for testing)
	    private static boolean checkExpenseInDatabase(int expenseId) {
	        return false; // In a real scenario, this would query the database
	    }
	}


