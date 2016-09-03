package com.vipinstraders.taxi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.vipinstraders.taxi.domain.ExpenseType;

@Component
@ImportResource("WEB-INF/spring-datasource.xml")
public class ExpenseTypeDaoImpl implements ExpenseTypeDao {

	private JdbcTemplate jdbcTemplate;

	public ExpenseTypeDaoImpl(@Qualifier("dataSource") BasicDataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void createExpenseType(ExpenseType expenseType) {
		this.jdbcTemplate.update("insert into expense_type  (name, description) values ('" + expenseType.getName()
				+ "', '" + expenseType.getDescription() + "')");
	}

	@Override
	public void editExpenseType(ExpenseType expenseType) {
		this.jdbcTemplate.update(
		        "update expense_type set name = ?, description = ? where id = ?",
		        expenseType.getName(), expenseType.getDescription(), expenseType.getId());

	}

	@Override
	public List<ExpenseType> getAllExpenseType() {
		List<ExpenseType> expenseTypes = this.jdbcTemplate.query(
		        "select id, name, description from expense_type",
		        new RowMapper<ExpenseType>() {
		            public ExpenseType mapRow(ResultSet rs, int rowNum) throws SQLException {
		            	ExpenseType expenseType = new ExpenseType();
		            	expenseType.setId(rs.getInt("id"));
		            	expenseType.setName(rs.getString("name"));
		            	expenseType.setDescription(rs.getString("description"));
		                return expenseType;
		            }
		        });
		return expenseTypes;
	}

	@Override
	public void deleteExpenseType(String id) {
		this.jdbcTemplate.update(
		        "delete from expense_type where id = ?",
		        Long.valueOf(id));
	}

}
