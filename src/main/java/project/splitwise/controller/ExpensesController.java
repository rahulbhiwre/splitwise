package project.splitwise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import project.splitwise.dto.ExpensesDTO;
import project.splitwise.serviceimpl.ExpensesImpl;

@RestController
@RequestMapping(value = "/api")
public class ExpensesController extends BaseController {

	@Autowired
	ExpensesImpl expenseService;

	@PostMapping("/expense")
	public ResponseEntity<ResponseModel<ExpensesDTO>> addUser(@RequestBody ExpensesDTO expense) {
		boolean status = expenseService.addExpense(expense);
		if (status) {
			return getResponseEntityForCreate(expense, "expense added");
		}
		return getResponseEntityForBadRequest(expense, "expense not added");
	}

	@GetMapping("/expense")
	public ResponseEntity<ResponseModel<Double>> getAllExpenses(Long friend1, Long friend2) {
		Double result = expenseService.calculateExpenses(friend1, friend2);
		System.out.println("Result.." + result);
		return getResponseEntityForOK(result, "expenses calculated");
	}

}
