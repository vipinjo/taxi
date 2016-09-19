package com.vipinstraders.taxi.service.transaction;

import java.util.List;

import com.vipinstraders.taxi.domain.Transaction;
import com.vipinstraders.taxi.object.criteria.TransactionSearchCriteria;

public interface ExpenseService {
	
	public void add(Transaction expense);
	
	public void update(Transaction expense);
	
	public void delete(int id);
	
	public List<Transaction> getAllExpenses(TransactionSearchCriteria searchCriteria);
	
	public Transaction getExpense(int id);
	

}
