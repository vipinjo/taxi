package com.vipinstraders.taxi.service.transaction;

import java.util.List;

import com.vipinstraders.taxi.domain.Expense;
import com.vipinstraders.taxi.object.criteria.ExpenseSearchCriteria;

public interface ExpenseService {
	
	public void add(Expense expense);
	
	public void update(Expense expense);
	
	public void delete(int id);
	
	public List<Expense> getAllExpenses(ExpenseSearchCriteria searchCriteria);
	
	public Expense getExpense(int id);
	

}
