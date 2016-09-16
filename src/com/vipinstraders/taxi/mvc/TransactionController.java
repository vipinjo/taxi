package com.vipinstraders.taxi.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vipinstraders.taxi.service.transaction.ExpenseService;

@Controller
public class TransactionController {
	
	private ExpenseService expenseService;
	
	@Autowired
	public TransactionController(ExpenseService expenseService) {
		this.expenseService = expenseService;
	}
	
	@RequestMapping("/transaction")
	public String showTransactionTab(Model model) {
		
		return "expenseReport";
	}
	
	@RequestMapping("/addExpense")
	public String addExpense(Model model) {
		
		return "addExpense";
	}

}
