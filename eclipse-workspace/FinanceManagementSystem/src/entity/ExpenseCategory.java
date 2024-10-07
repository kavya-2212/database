
	package entity;

	public class ExpenseCategory {
	    private int categoryId;
	    private String categoryName;

	    // Default Constructor
	    public ExpenseCategory() {}

	    // Parameterized Constructor
	    public ExpenseCategory(int categoryId, String categoryName) {
	        this.categoryId = categoryId;
	        this.categoryName = categoryName;
	    }

	    // Getters and Setters
	    public int getCategoryId() {
	        return categoryId;
	    }

	    public void setCategoryId(int categoryId) {
	        this.categoryId = categoryId;
	    }

	    public String getCategoryName() {
	        return categoryName;
	    }

	    public void setCategoryName(String categoryName) {
	        this.categoryName = categoryName;
	    }

	    // Main method for testing
	    public static void main(String[] args) {
	        // Create an ExpenseCategory instance
	        ExpenseCategory category = new ExpenseCategory(1, "Transportation");

	        // Test getters
	        System.out.println("Category ID: " + category.getCategoryId());
	        System.out.println("Category Name: " + category.getCategoryName());

	        // Test setters
	        category.setCategoryName("Car Transportation");

	        System.out.println("Updated Category Name: " + category.getCategoryName());
	    }
	}


