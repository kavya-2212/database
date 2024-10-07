package main;

import dao.FinanceRepositoryImpl;
import entity.Expense;
import entity.User;
import exception.UserNotFoundException;
import exception.ExpenseNotFoundException;

import java.util.List;
import java.util.Scanner;

public class FinanceApp {

    public static void main(String[] args) {
        FinanceRepositoryImpl financeRepo = new FinanceRepositoryImpl();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            // Display Menu
            System.out.println("\n===== Finance Management Application =====");
            System.out.println("1. Add User");
            System.out.println("2. Add Expense");
            System.out.println("3. Delete User");
            System.out.println("4. Delete Expense");
            System.out.println("5. Update Expense");
            System.out.println("6. View All Expenses");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // Add User
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();

                    User newUser = new User(0, username, password, email);
                    boolean isUserAdded = financeRepo.createUser(newUser);
                    System.out.println(isUserAdded ? "User added successfully." : "Failed to add user.");
                    break;

                case 2:
                    // Add Expense
                    System.out.print("Enter user ID: ");
                    int userId = scanner.nextInt();
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    System.out.print("Enter category ID: ");
                    int categoryId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();

                    Expense newExpense = new Expense(0, userId, amount, categoryId, date, description);
                    boolean isExpenseAdded = financeRepo.createExpense(newExpense);
                    System.out.println(isExpenseAdded ? "Expense added successfully." : "Failed to add expense.");
                    break;

                case 3:
                    // Delete User
                    System.out.print("Enter user ID to delete: ");
                    int userIdToDelete = scanner.nextInt();
                    try {
                        boolean isUserDeleted = financeRepo.deleteUser(userIdToDelete);
                        System.out.println(isUserDeleted ? "User deleted successfully." : "Failed to delete user.");
                    } catch (UserNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    // Delete Expense
                    System.out.print("Enter expense ID to delete: ");
                    int expenseIdToDelete = scanner.nextInt();
                    try {
                        boolean isExpenseDeleted = financeRepo.deleteExpense(expenseIdToDelete);
                        System.out.println(isExpenseDeleted ? "Expense deleted successfully." : "Failed to delete expense.");
                    } catch (ExpenseNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    // Update Expense
                    System.out.print("Enter expense ID to update: ");
                    int expenseIdToUpdate = scanner.nextInt();
                    System.out.print("Enter new amount: ");
                    double newAmount = scanner.nextDouble();
                    System.out.print("Enter new category ID: ");
                    int newCategoryId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter new date (YYYY-MM-DD): ");
                    String newDate = scanner.nextLine();
                    System.out.print("Enter new description: ");
                    String newDescription = scanner.nextLine();

                    Expense updatedExpense = new Expense(expenseIdToUpdate, 0, newAmount, newCategoryId, newDate, newDescription);
                    boolean isExpenseUpdated = financeRepo.updateExpense(expenseIdToUpdate, updatedExpense);
                    System.out.println(isExpenseUpdated ? "Expense updated successfully." : "Failed to update expense.");
                    break;

                case 6:
                    // View All Expenses for a User
                    System.out.print("Enter user ID to view expenses: ");
                    int userIdForExpenses = scanner.nextInt();
                    List<Expense> expenses = financeRepo.getAllExpenses(userIdForExpenses);
                    if (expenses.isEmpty()) {
                        System.out.println("No expenses found for the user.");
                    } else {
                        System.out.println("\n--- Expenses for User ID " + userIdForExpenses + " ---");
                        for (Expense expense : expenses) {
                            System.out.println(expense);
                        }
                    }
                    break;

                case 7:
                    // Exit
                    running = false;
                    System.out.println("Exiting the application...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
