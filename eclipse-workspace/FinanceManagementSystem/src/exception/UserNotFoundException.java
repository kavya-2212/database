package exception;

	// Custom Exception Class
	public class UserNotFoundException extends Exception {
	    public UserNotFoundException(String message) {
	        super(message);
	    }

	    // Main method for testing the exception
	    public static void main(String[] args) {
	        try {
	            // Simulate a scenario where the user is not found
	            int userId = 10;  // Example userId that doesn't exist
	            boolean userExists = checkUserInDatabase(userId);

	            if (!userExists) {
	                throw new UserNotFoundException("User with ID " + userId + " not found.");
	            }

	        } catch (UserNotFoundException e) {
	            System.out.println(e.getMessage());
	        }
	    }

	    // Simulate database user lookup (returns false for testing)
	    private static boolean checkUserInDatabase(int userId) {
	        return false; // In a real scenario, this would query the database
	    }
	}

