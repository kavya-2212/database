
	package entity;

	public class User {
	    private int userId;
	    private String username;
	    private String password;
	    private String email;

	    // Default Constructor
	    public User() {}

	    // Parameterized Constructor
	    public User(int userId, String username, String password, String email) {
	        this.userId = userId;
	        this.username = username;
	        this.password = password;
	        this.email = email;
	    }

	    // Getters and Setters
	    public int getUserId() {
	        return userId;
	    }

	    public void setUserId(int userId) {
	        this.userId = userId;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }
	    public static void main(String[] args) {
	        // Create a User instance
	        User user = new User(1, "joen", "joen123", "joen@gmail.com");

	        // Test getters
	        System.out.println("User ID: " + user.getUserId());
	        System.out.println("Username: " + user.getUsername());
	        System.out.println("Email: " + user.getEmail());

	        // Test setters
	        user.setUsername("joen_jungkook");
	        user.setEmail("jungkook@gmail.com");

	        System.out.println("Updated Username: " + user.getUsername());
	        System.out.println("Updated Email: " + user.getEmail());
	    
	    }
	}


