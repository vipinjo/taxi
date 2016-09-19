package com.vipinstraders.taxi.service.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vipinstraders.taxi.dao.TransactionDao;
import com.vipinstraders.taxi.domain.Transaction;
import com.vipinstraders.taxi.object.criteria.TransactionSearchCriteria;

@Component
public class ExpenseServiceImpl implements ExpenseService {
	
	TransactionDao dao;
	
	@Autowired
	public ExpenseServiceImpl(TransactionDao dao) {
		this.dao = dao;
	}

	@Override
	public void add(Transaction expense) {
		dao.save(expense);
	}

	@Override
	public void update(Transaction expense) {
		dao.edit(expense);

	}

	@Override
	public void delete(int id) {
		dao.delete(id);

	}

	@Override
	public List<Transaction> getAllExpenses(TransactionSearchCriteria searchCriteria) {
		
		return dao.getTransactionList(searchCriteria);
	}

	@Override
	public Transaction getExpense(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
