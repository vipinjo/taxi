package com.vipinstraders.taxi.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import com.vipinstraders.taxi.domain.ExpenseType;
import com.vipinstraders.taxi.domain.Transaction;
import com.vipinstraders.taxi.object.criteria.TransactionSearchCriteria;

@Component
public class TransactionDaoImpl implements TransactionDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public TransactionDaoImpl(@Qualifier("dataSource") BasicDataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void save(Transaction transaction) {
		Date transactionDate = new Date(transaction.getDate().getTime());
		
		SimpleJdbcInsert insertShiftReport = new SimpleJdbcInsert(jdbcTemplate)
		        .withTableName("transaction")
		        .usingColumns("transaction_date", "expense_type_id", 
		        		"expense_amount", "meter_reading",
		        		"description")
		        .usingGeneratedKeyColumns("id");
				
				Map<String, Object> parameters = new HashMap<String, Object>();
		        parameters.put("transaction_date", transactionDate);
		        if (transaction.getExpenseType() != null) {
		            parameters.put("expense_type_id", transaction.getExpenseType().getId());
		        }
		        parameters.put("expense_amount", transaction.getExpenseAmount());
		        parameters.put("meter_reading", transaction.getMeterReading());
		        parameters.put("description", transaction.getDescription());
		        insertShiftReport.executeAndReturnKey(parameters);
		

	}

	@Override
	public void edit(Transaction transaction) {
		StringBuffer sql = new StringBuffer("UPDATE transaction SET transaction_date=?, ")
				.append("expense_type_id=?, expense_amount=?, meter_reading=?, description=? where id=?");
		jdbcTemplate.update(sql.toString(),
				new Object[] { transaction.getDate(), (transaction.getExpenseType() != null)?transaction.getExpenseType().getId():null,
						transaction.getExpenseAmount(), transaction.getMeterReading(), transaction.getDescription(),
						transaction.getId() });
	}

	@Override
	public void delete(int id) {
		this.jdbcTemplate.update(
		        "DELETE FROM transaction WHERE id = ?",
		        Long.valueOf(id));
	}

	@Override
	public Transaction getTransaction(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getTransactionList(TransactionSearchCriteria searchCriteria) {
		
		StringBuffer query = new StringBuffer("SELECT transaction.id as transaction_id, transaction_date, ")
				.append("expense_type.id as expense_id, expense_type.name as expense_type_name, ")
				.append("income_type.id as income_id, income_type.name as income_type_name,")
				.append("expense_amount, meter_reading, ")
				.append("transaction.description")
				.append(" FROM transaction ")
				.append("LEFT JOIN expense_type ON transaction.expense_type_id = expense_type.id ")
				.append("LEFT JOIN income_type ON transaction.expense_type_id = income_type.id ");
		
		if (searchCriteria.getStartDate() != null && searchCriteria.getEndDate() == null) {
			Date startDate = new Date(searchCriteria.getStartDate().getTime());
			query.append("where transaction_date >= '" + startDate + " 00:00:00' ");
		} else if (searchCriteria.getStartDate() != null && searchCriteria.getEndDate() != null) {
			Date startDate = new Date(searchCriteria.getStartDate().getTime());
			Date finishDate = new Date(searchCriteria.getEndDate().getTime());
			query.append("where transaction_date BETWEEN ").append("'" + startDate + " 00:00:00'").append(" AND ")
					.append("'" + finishDate + " 23:59:59' ");
		}
		
		query.append("ORDER BY transaction_date");
		
		List<Transaction> transactions = this.jdbcTemplate.query(query.toString(), new RowMapper<Transaction>() {
			public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
				Transaction transaction = new Transaction();
				transaction.setId(rs.getInt("transaction_id"));
				transaction.setDate(rs.getTimestamp("transaction_date"));
				ExpenseType expenseType = new ExpenseType();
				expenseType.setId(rs.getInt("expense_id"));
				expenseType.setName(rs.getString("expense_type_name"));
				transaction.setExpenseType(expenseType);
				transaction.setExpenseAmount(rs.getDouble("expense_amount"));
				transaction.setMeterReading(rs.getInt("meter_reading"));
				transaction.setDescription(rs.getString("description"));
				return transaction;
			}
		});
		
		return transactions;
		
	}

}
