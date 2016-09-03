package com.vipinstraders.taxi.service.admin;

import java.util.List;

import com.vipinstraders.taxi.domain.ExpenseType;

public interface ExpenseTypeService {
	
	public void addExpenseType(ExpenseType expenseType);
	
	public void editExpenseType(ExpenseType expenseType);
	
	public void deleteExpenseType(String id);
	
	public List<ExpenseType> getAll();

}
