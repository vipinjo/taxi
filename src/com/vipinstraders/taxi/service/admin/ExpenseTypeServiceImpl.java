package com.vipinstraders.taxi.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vipinstraders.taxi.dao.ExpenseTypeDao;
import com.vipinstraders.taxi.domain.ExpenseType;

@Component
public class ExpenseTypeServiceImpl implements ExpenseTypeService {

	private ExpenseTypeDao expenseTypeDao;

	@Autowired
	public ExpenseTypeServiceImpl(ExpenseTypeDao expenseTypeDao) {
		this.expenseTypeDao = expenseTypeDao;
	}

	@Override
	public void addExpenseType(ExpenseType expenseType) {
		expenseTypeDao.createExpenseType(expenseType);
	}

	@Override
	public void editExpenseType(ExpenseType expenseType) {
		expenseTypeDao.editExpenseType(expenseType);
	}

	@Override
	public void deleteExpenseType(String id) {
		expenseTypeDao.deleteExpenseType(id);
	}

	@Override
	public List<ExpenseType> getAll() {
		return expenseTypeDao.getAllExpenseType();
	}

}
