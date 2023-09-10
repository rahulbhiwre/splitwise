package project.splitwise.service;

import project.splitwise.dto.ExpensesDTO;

import java.util.List;

public interface Expenses {

    public boolean addExpense(ExpensesDTO expensesDTO);
    
    public List<ExpensesDTO> getAllExpenses();

    public ExpensesDTO getExpenseById(int id);

	void calculateExpenses(Long user1, Long user2);


}
