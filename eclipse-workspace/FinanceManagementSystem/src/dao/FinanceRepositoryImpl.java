package dao;

import entity.Expense;
import entity.User;
import exception.ExpenseNotFoundException;
import exception.UserNotFoundException;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FinanceRepositoryImpl implements IFinanceRepository {
    
    // Method to establish a database connection
    private Connection getConnection() {
        return DBConnection.getConnection(); // Assume DBConnUtil is properly implemented
    }

    @Override
    public boolean createUser(User user) {
        String query = "INSERT INTO Users (username, password, email) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean createExpense(Expense expense) {
        String query = "INSERT INTO Expenses (user_id, amount, category_id, date, description) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, expense.getUserId());
            pstmt.setDouble(2, expense.getAmount());
            pstmt.setInt(3, expense.getCategoryId());
            pstmt.setDate(4, Date.valueOf(expense.getDate())); // Assuming date is in the format YYYY-MM-DD
            pstmt.setString(5, expense.getDescription());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUser(int userId) throws UserNotFoundException {
        if (!userExists(userId)) {
            throw new UserNotFoundException("User with ID " + userId + " not found.");
        }
        String query = "DELETE FROM Users WHERE user_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteExpense(int expenseId) throws ExpenseNotFoundException {
        if (!expenseExists(expenseId)) {
            throw new ExpenseNotFoundException("Expense with ID " + expenseId + " not found.");
        }
        String query = "DELETE FROM Expenses WHERE expense_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, expenseId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateExpense(int expenseId, Expense expense) {
        String query = "UPDATE Expenses SET amount = ?, category_id = ?, date = ?, description = ? WHERE expense_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setDouble(1, expense.getAmount());
            pstmt.setInt(2, expense.getCategoryId());
            pstmt.setDate(3, Date.valueOf(expense.getDate())); // Assuming date is in the format YYYY-MM-DD
            pstmt.setString(4, expense.getDescription());
            pstmt.setInt(5, expenseId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Expense> getAllExpenses(int userId) {
        List<Expense> expenses = new ArrayList<>();
        String query = "SELECT * FROM Expenses WHERE user_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Expense expense = new Expense();
                expense.setExpenseId(rs.getInt("expense_id"));
                expense.setUserId(rs.getInt("user_id"));
                expense.setAmount(rs.getDouble("amount"));
                expense.setCategoryId(rs.getInt("category_id"));
                expense.setDate(rs.getDate("date").toString());
                expense.setDescription(rs.getString("description"));
                expenses.add(expense);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenses;
    }

    // Helper method to check if user exists
    private boolean userExists(int userId) {
        String query = "SELECT COUNT(*) FROM Users WHERE user_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Helper method to check if expense exists
    private boolean expenseExists(int expenseId) {
        String query = "SELECT COUNT(*) FROM Expenses WHERE expense_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, expenseId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Main method to demonstrate the functionality
    public static void main(String[] args) {
        FinanceRepositoryImpl financeRepo = new FinanceRepositoryImpl();

        // Creating a new user
        User newUser = new User(1, "joen_bam", "securePassword", "joen@gmail.com");
        if (financeRepo.createUser(newUser)) {
            System.out.println("User created successfully.");
        } else {
            System.out.println("Failed to create user.");
        }

        // Creating a new expense
        Expense newExpense = new Expense(6, 1, 500.0, 6, "2024-10-05", "Grocery shopping");
        if (financeRepo.createExpense(newExpense)) {
            System.out.println("Expense created successfully.");
        } else {
            System.out.println("Failed to create expense.");
        }

        // Fetching all expenses for a user
        List<Expense> expenses = financeRepo.getAllExpenses(3); // Replace 1 with the actual user ID
        System.out.println("Expenses for User ID 3:");
        for (Expense expense : expenses) {
            System.out.println(expense);
        }

        // Updating an expense
        if (!expenses.isEmpty()) {
            Expense expenseToUpdate = expenses.get(4);
            expenseToUpdate.setAmount(1000.0); // Update amount
            if (financeRepo.updateExpense(expenseToUpdate.getExpenseId(), expenseToUpdate)) {
                System.out.println("Expense updated successfully.");
            } else {
                System.out.println("Failed to update expense.");
            }
        }

        // Deleting an expense
        if (!expenses.isEmpty()) {
            try {
                if (financeRepo.deleteExpense(expenses.get(2).getExpenseId())) {
                    System.out.println("Expense deleted successfully.");
                }
            } catch (ExpenseNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        // Deleting a user
        try {
            if (financeRepo.deleteUser(3)) { // Replace 1 with the actual user ID
                System.out.println("User deleted successfully.");
            }
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
