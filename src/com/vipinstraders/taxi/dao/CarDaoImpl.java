package com.vipinstraders.taxi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

	@Override
	public List<Car> getAllCars() {
		List<Car> cars = this.jdbcTemplate.query(
		        "select id, rego, make from car",
		        new RowMapper<Car>() {
		            public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
		                Car car = new Car();
		                car.setId(rs.getInt("id"));
		                car.setRego(rs.getString("rego"));
		                car.setMake(rs.getString("make"));
		                return car;
		            }
		        });
		return cars;
	}

}
