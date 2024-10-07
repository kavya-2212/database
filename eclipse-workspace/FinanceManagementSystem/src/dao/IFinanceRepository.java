package dao;

import entity.Expense;
import entity.User;
import exception.ExpenseNotFoundException;
import exception.UserNotFoundException;

import java.util.List;

public interface IFinanceRepository {

    boolean createUser(User user);

    boolean createExpense(Expense expense);

    boolean deleteUser(int userId) throws UserNotFoundException;

    boolean deleteExpense(int expenseId) throws ExpenseNotFoundException;

    boolean updateExpense(int expenseId, Expense expense);

    List<Expense> getAllExpenses(int userId);
    
}
