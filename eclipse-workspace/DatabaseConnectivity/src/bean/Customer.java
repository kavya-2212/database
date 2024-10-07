package bean;

public class Customer {
    private long customerId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String address;

    public Customer(long customerId, String firstName, String lastName, String emailAddress, String phoneNumber, String address) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    // Getter methods for each private field
    public long getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public static void main(String[] args) {
        // Creating a customer object
        Customer customer = new Customer(101, "Joen", "Bam", "joen.bam@gmail.com", "1234567890", "vizag");

        // Displaying customer details using getter methods
        System.out.println("Customer Details:");
        System.out.println("ID: " + customer.getCustomerId());
        System.out.println("Name: " + customer.getFirstName() + " " + customer.getLastName());
        System.out.println("Email: " + customer.getEmailAddress());
        System.out.println("Phone: " + customer.getPhoneNumber());
        System.out.println("Address: " + customer.getAddress());
    }
}
