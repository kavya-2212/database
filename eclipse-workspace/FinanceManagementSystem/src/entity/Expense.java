
	package entity;

	public class Expense {
	    private int expenseId;
	    private int userId;
	    private double amount;
	    private int categoryId;
	    private String date;
	    private String description;

	    // Default Constructor
	    public Expense() {}

	    // Parameterized Constructor
	    public Expense(int expenseId, int userId, double amount, int categoryId, String date, String description) {
	        this.expenseId = expenseId;
	        this.userId = userId;
	        this.amount = amount;
	        this.categoryId = categoryId;
	        this.date = date;
	        this.description = description;
	    }

	    // Getters and Setters
	    public int getExpenseId() {
	        return expenseId;
	    }

	    public void setExpenseId(int expenseId) {
	        this.expenseId = expenseId;
	    }

	    public int getUserId() {
	        return userId;
	    }

	    public void setUserId(int userId) {
	        this.userId = userId;
	    }

	    public double getAmount() {
	        return amount;
	    }

	    public void setAmount(double amount) {
	        this.amount = amount;
	    }

	    public int getCategoryId() {
	        return categoryId;
	    }

	    public void setCategoryId(int categoryId) {
	        this.categoryId = categoryId;
	    }

	    public String getDate() {
	        return date;
	    }

	    public void setDate(String date) {
	        this.date = date;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    // Main method for testing
	    public static void main(String[] args) {
	        // Create an Expense instance
	        Expense expense = new Expense(1, 101, 250.75, 3, "2024-10-05", "Groceries");

	        // Test getters
	        System.out.println("Expense ID: " + expense.getExpenseId());
	        System.out.println("User ID: " + expense.getUserId());
	        System.out.println("Amount: " + expense.getAmount());
	        System.out.println("Category ID: " + expense.getCategoryId());
	        System.out.println("Date: " + expense.getDate());
	        System.out.println("Description: " + expense.getDescription());

	        // Test setters
	        expense.setAmount(300.50);
	        expense.setDescription("Tea Groceries");

	        System.out.println("Updated Amount: " + expense.getAmount());
	        System.out.println("Updated Description: " + expense.getDescription());
	    }
	}


