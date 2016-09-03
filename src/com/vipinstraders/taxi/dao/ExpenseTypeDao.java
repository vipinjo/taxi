package com.vipinstraders.taxi.dao;

import java.util.List;

import com.vipinstraders.taxi.domain.ExpenseType;

public interface ExpenseTypeDao {
	
	public void createExpenseType(ExpenseType expenseType);
	
	public void editExpenseType(ExpenseType expenseType);
	
	public List<ExpenseType> getAllExpenseType();
	
	public void deleteExpenseType(String id);

}
