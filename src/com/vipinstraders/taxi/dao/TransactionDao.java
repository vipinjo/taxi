package com.vipinstraders.taxi.dao;

import java.util.List;

import com.vipinstraders.taxi.domain.Transaction;
import com.vipinstraders.taxi.object.criteria.TransactionSearchCriteria;

public interface TransactionDao {

	public void save(Transaction transaction);

	public void edit(Transaction transaction);

	public void delete(int id);

	public Transaction getTransaction(int id);

	public List<Transaction> getTransactionList(TransactionSearchCriteria searchCriteria);

}
