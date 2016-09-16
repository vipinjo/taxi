package com.vipinstraders.taxi.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vipinstraders.taxi.domain.ExpenseType;
import com.vipinstraders.taxi.service.admin.ExpenseTypeService;
import com.vipinstraders.taxi.service.transaction.ExpenseService;

@Controller
public class TransactionController {
	
	private ExpenseService expenseService;
	private ExpenseTypeService expenseTypeService;
	
	@Autowired
	public TransactionController(ExpenseService expenseService, ExpenseTypeService expenseTypeService) {
		this.expenseService = expenseService;
		this.expenseTypeService = expenseTypeService;
	}
	
	@RequestMapping("/transaction")
	public String showTransactionTab(Model model) {
		
		return "expenseReport";
	}
	
	@RequestMapping("/addExpense")
	public String addExpense(Model model) {
		List<ExpenseType> expenseTypeList = expenseTypeService.getAll();
		model.addAttribute("expenseTypeList", expenseTypeList);
		
		return "addExpense";
	}

}
