package project.splitwise.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.splitwise.dto.ExpensesDTO;
import project.splitwise.entity.ExpensesEntity;
import project.splitwise.entity.UserTotalAmountEntity;
import project.splitwise.repository.IAccountRepository;
import project.splitwise.repository.IExpensesRepository;

@Service
public class ExpensesImpl {

	@Autowired
	IExpensesRepository iExpensesRepository;

	@Autowired
	IAccountRepository iAccountRepository;

	@Autowired
	ModelMapper modelMapper;

	public boolean addExpense(ExpensesDTO expensesDTO) {
		ExpensesEntity newExpenses = this.modelMapper.map(expensesDTO, ExpensesEntity.class);
		iExpensesRepository.save(newExpenses);
		// update totalAmount table
		updateTotalAmountTable(newExpenses);
		return true;
	}

	private void updateTotalAmountTable(ExpensesEntity newExpenses) {
		Long borrower = newExpenses.getTo().getUserid();
		Long lender = newExpenses.getFrom().getUserid();

		Double borrowerAmount = newExpenses.getAmount();
		Double lenderAmount = newExpenses.getAmount();

		UserTotalAmountEntity borrowerAccount = iAccountRepository.findByUserId(borrower);
		UserTotalAmountEntity lenderAccount = iAccountRepository.findByUserId(lender);
		borrowerAccount.setAmount(borrowerAccount.getAmount() - borrowerAmount);
		lenderAccount.setAmount(lenderAccount.getAmount() + lenderAmount);
		iAccountRepository.save(borrowerAccount);
	}

	public Double calculateExpenses(Long friend1, Long friend2) {

		List<ExpensesEntity> expenses = iExpensesRepository.findExpensesByUserIdss(friend1, friend2);
		System.out.println(expenses);
		List<ExpensesDTO> expensesDTO = expenses.stream().map(expense -> modelMapper.map(expense, ExpensesDTO.class))
				.collect(Collectors.toList());

		expensesDTO.forEach(exp -> System.out.println(exp.toString()));

		Double Borrow = expensesDTO.stream().filter(expense -> expense.getFrom().getUserid().equals(friend1))
				.mapToDouble(ExpensesDTO::getAmount).reduce(0.0, (a, b) -> a + b);

		Double Lend = expensesDTO.stream().filter(expense -> expense.getTo().getUserid().equals(friend1))
				.mapToDouble(ExpensesDTO::getAmount).reduce(0.0, (a, b) -> a + b);

		System.out.println("Borrow.." + Borrow);
		System.out.println("Lend.." + Lend);

		Double finalAmount = Borrow - Lend;

		// use trancasationDTO

		return finalAmount;

	}

}
