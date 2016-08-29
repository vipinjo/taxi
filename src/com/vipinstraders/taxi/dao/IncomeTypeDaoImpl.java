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

import com.vipinstraders.taxi.domain.IncomeType;

@Component
@ImportResource("WEB-INF/spring-datasource.xml")
public class IncomeTypeDaoImpl implements IncomeTypeDao {

	private JdbcTemplate jdbcTemplate;

	public IncomeTypeDaoImpl(@Qualifier("dataSource") BasicDataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void createIncomeType(IncomeType incomeType) {
		this.jdbcTemplate.update("insert into income_type  (name, description) values ('" + incomeType.getName()
				+ "', '" + incomeType.getDescription() + "')");
	}

	@Override
	public void editIncomeType(IncomeType incomeType) {
		this.jdbcTemplate.update(
		        "update income_type set name = ?, description = ? where id = ?",
		        incomeType.getName(), incomeType.getDescription(), incomeType.getId());

	}

	@Override
	public List<IncomeType> getAllIncomeType() {
		List<IncomeType> incomeTypes = this.jdbcTemplate.query(
		        "select id, name, description from income_type",
		        new RowMapper<IncomeType>() {
		            public IncomeType mapRow(ResultSet rs, int rowNum) throws SQLException {
		            	IncomeType incomeType = new IncomeType();
		            	incomeType.setId(rs.getInt("id"));
		            	incomeType.setName(rs.getString("name"));
		            	incomeType.setDescription(rs.getString("description"));
		                return incomeType;
		            }
		        });
		return incomeTypes;
	}

	@Override
	public void deleteIncomeType(String id) {
		this.jdbcTemplate.update(
		        "delete from income_type where id = ?",
		        Long.valueOf(id));

	}

}
