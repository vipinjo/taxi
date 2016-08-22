package com.vipinstraders.taxi.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.vipinstraders.taxi.domain.Car;

@Component
@ImportResource("WEB-INF/spring-datasource.xml")
public class CarDaoImpl implements CarDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public CarDaoImpl(@Qualifier("dataSource") BasicDataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void createCar(Car car) {
		this.jdbcTemplate
				.update("insert into car  (make, rego) values ('" + car.getMake() + "', '" + car.getRego() + "')");
	}

}
