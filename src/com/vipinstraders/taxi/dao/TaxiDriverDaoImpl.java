package com.vipinstraders.taxi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import com.vipinstraders.taxi.domain.Driver;

@Component
@ImportResource("WEB-INF/spring-datasource.xml")
public class TaxiDriverDaoImpl implements TaxiDriverDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public TaxiDriverDaoImpl(@Qualifier("dataSource") BasicDataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void addDriver(Driver driver) {
		SimpleJdbcInsert insertDriver = new SimpleJdbcInsert(jdbcTemplate).withTableName("driver")
				.usingColumns("family_name", "given_name", "dc", "abn").usingGeneratedKeyColumns("id");
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("family_name", driver.getFamilyName());
        parameters.put("given_name", driver.getGivenName());
        parameters.put("dc", driver.getDc());
        parameters.put("abn", driver.getABN());
        insertDriver.executeAndReturnKey(parameters);
	}

	@Override
	public void editDriver(Driver driver) {
		StringBuffer sql = new StringBuffer("UPDATE driver SET family_name=?, ")
				.append("given_name=?, dc=?, abn=? where id=?");
		jdbcTemplate.update(sql.toString(), new Object[]{driver.getFamilyName(), driver.getGivenName(),
				driver.getDc(), driver.getABN(), driver.getId()});

	}

	@Override
	public void deleteDriver(int id) {
		this.jdbcTemplate.update(
		        "delete from driver where id = ?",
		        Long.valueOf(id));
	}

	@Override
	public List<Driver> getAllDrivers() {
		List<Driver> drivers = this.jdbcTemplate.query(
		        "select id, family_name, given_name, dc, abn from driver",
		        new RowMapper<Driver>() {
		            public Driver mapRow(ResultSet rs, int rowNum) throws SQLException {
		            	Driver driver = new Driver();
		            	driver.setId(rs.getInt("id"));
		            	driver.setFamilyName(rs.getString("family_name"));
		            	driver.setGivenName(rs.getString("given_name"));
		            	driver.setDc(rs.getString("dc"));
		            	driver.setABN(rs.getString("abn"));
		                return driver;
		            }
		        });
		return drivers;
	}

}
