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
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import com.vipinstraders.taxi.domain.Car;
import com.vipinstraders.taxi.domain.Driver;
import com.vipinstraders.taxi.domain.ShiftReport;
import com.vipinstraders.taxi.object.criteria.ShiftReportSearchCriteria;

@Component
@ImportResource("WEB-INF/spring-datasource.xml")
public class ShiftReportDaoImpl implements ShiftReportDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public ShiftReportDaoImpl(@Qualifier("dataSource") BasicDataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void add(ShiftReport shiftReport) {
		Date startDate = new Date(shiftReport.getDate().getTime());
		Date finishDate = new Date(shiftReport.getFinishDate().getTime());
		
		SimpleJdbcInsert insertShiftReport = new SimpleJdbcInsert(jdbcTemplate)
        .withTableName("shift_report")
        .usingColumns("report_date", "finish_date", "car_id", "driver_id",
        		"start_meter_reading", "end_meter_reading", "meter_rev", 
        		"owner_rev", "owner_subsidy", "bailment_fee", "paper_voucher", 
        		"fuel_receipt", "online_receipt", "account_voucher", 
        		"driver_revenue", "driver_subsidy", "total")
        .usingGeneratedKeyColumns("id");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("report_date", startDate);
        parameters.put("finish_date", finishDate);
        parameters.put("car_id", shiftReport.getCar().getId());
        parameters.put("driver_id", shiftReport.getDriver().getId());
        parameters.put("start_meter_reading", shiftReport.getStartMeterReading());
        parameters.put("end_meter_reading", shiftReport.getEndMeterReading());
        parameters.put("meter_rev", shiftReport.getMeterRevenue());
        parameters.put("owner_rev", shiftReport.getOwnerRevenue());
        parameters.put("owner_subsidy", shiftReport.getOwnerSubsidy());
        parameters.put("bailment_fee", shiftReport.getBailmentFee());
        parameters.put("paper_voucher", shiftReport.getPaperVoucher());
        parameters.put("fuel_receipt", shiftReport.getFuelReceipt());
        parameters.put("online_receipt",shiftReport.getOnlineReceipt());
        parameters.put("account_voucher",shiftReport.getAccountVoucher());
        parameters.put("driver_revenue",shiftReport.getDriverRevenue());
        parameters.put("driver_subsidy",shiftReport.getDriverSubsidy());
        parameters.put("total", shiftReport.getTotal());
        insertShiftReport.executeAndReturnKey(parameters);
		
//		this.jdbcTemplate.update("insert into shift_report  (report_date, finish_date, meter_rev, "
//				+ "owner_subsidy, bailment_fee, paper_voucher, fuel_receipt, " + "online_receipt, total) values ("
//				+ startDate + "," + finishDate + ", "
//				+ shiftReport.getMeterRevenue() + ", " + shiftReport.getOwnerSubsidy() + ", "
//				+ shiftReport.getBailmentFee() + ", " + shiftReport.getPaperVoucher() + " , "
//				+ shiftReport.getFuelReceipt() + ", " + shiftReport.getOnlineReceipt() + ", " + shiftReport.getTotal()
//				+ " )");

	}

	@Override
	public void update(ShiftReport shiftReport) {
		StringBuffer sql = new StringBuffer("UPDATE shift_report SET report_date=?, finish_date=?, ")
				.append("car_id=?, driver_id=?, ")
				.append("start_meter_reading=?, end_meter_reading=?, ")
				.append("meter_rev=?, owner_rev=?, owner_subsidy=?, bailment_fee=?, ")
				.append("paper_voucher=?, fuel_receipt=?, online_receipt=?, ")
				.append("account_voucher=?, driver_revenue=?, driver_subsidy=?, total=? WHERE id=?");
		
        jdbcTemplate.update(sql.toString(), new Object[]{shiftReport.getDate(), shiftReport.getFinishDate(),
        		shiftReport.getCar().getId(), shiftReport.getDriver().getId(),
        		shiftReport.getStartMeterReading(), shiftReport.getEndMeterReading(),
        		shiftReport.getMeterRevenue(), shiftReport.getOwnerRevenue(), shiftReport.getOwnerSubsidy(),
        		shiftReport.getBailmentFee(), shiftReport.getPaperVoucher(), shiftReport.getFuelReceipt(), shiftReport.getOnlineReceipt(),
        		shiftReport.getAccountVoucher(), shiftReport.getDriverRevenue(), shiftReport.getDriverSubsidy(),
        		shiftReport.getTotal(), shiftReport.getId()});
	}

	@Override
	public void delete(int id) {
		this.jdbcTemplate.update(
		        "DELETE FROM shift_report WHERE id = ?",
		        Long.valueOf(id));
	}

	@Override
	public List<ShiftReport> getShiftReport(ShiftReportSearchCriteria searchCriteria) {
		
		StringBuffer query = new StringBuffer("SELECT shift_report.id, report_date, finish_date, car.id as car_id, ")
				.append("car.rego as car_rego, car.make as car_make, ")
				.append("driver.id as driver_id, driver.family_name as driver_family_name, ")
				.append("driver.given_name as driver_given_name, driver.dc as driver_dc, driver.abn as driver_abn, ")
				.append("start_meter_reading, end_meter_reading, ")
				.append("meter_rev, owner_rev, owner_subsidy, bailment_fee, ")
				.append("paper_voucher, fuel_receipt, online_receipt, ")
				.append("account_voucher, driver_revenue, driver_subsidy, ")
				.append("total FROM shift_report ")
				.append("INNER JOIN car ON shift_report.car_id = car.id ")
				.append("INNER JOIN driver ON shift_report.driver_id = driver.id ");
		if (searchCriteria.getStartDate() != null && searchCriteria.getEndDate() == null) {
			Date startDate = new Date(searchCriteria.getStartDate().getTime());
			query.append("where report_date >= '" + startDate + " 00:00:00' ");
		} else if (searchCriteria.getStartDate() != null && searchCriteria.getEndDate() != null) {
			Date startDate = new Date(searchCriteria.getStartDate().getTime());
			Date finishDate = new Date(searchCriteria.getEndDate().getTime());
			query.append("where report_date BETWEEN ").append("'" + startDate + " 00:00:00'").append(" AND ")
					.append("'" + finishDate + " 23:59:59'");
		}
		List<ShiftReport> shiftReports = this.jdbcTemplate.query(query.toString(), new RowMapper<ShiftReport>() {
			public ShiftReport mapRow(ResultSet rs, int rowNum) throws SQLException {
				ShiftReport shiftReport = new ShiftReport();
				shiftReport.setId(rs.getInt("id"));
				shiftReport.setDate(rs.getTimestamp("report_date"));
				shiftReport.setFinishDate(rs.getTimestamp("finish_date"));
				Car car = new Car();
				car.setId(rs.getInt("car_id"));
				car.setRego(rs.getString("car_rego"));
				car.setMake(rs.getString("car_make"));
				shiftReport.setCar(car);
				Driver driver = new Driver();
				driver.setId(rs.getInt("driver_id"));
				driver.setFamilyName(rs.getString("driver_family_name"));
				driver.setGivenName(rs.getString("driver_given_name"));
				driver.setDc(rs.getString("driver_dc"));
				driver.setABN(rs.getString("driver_abn"));
				shiftReport.setDriver(driver);
				shiftReport.setStartMeterReading(rs.getInt("start_meter_reading"));
				shiftReport.setEndMeterReading(rs.getInt("end_meter_reading"));
				shiftReport.setMeterRevenue(rs.getDouble("meter_rev"));
				shiftReport.setOwnerRevenue(rs.getDouble("owner_rev"));
				shiftReport.setOwnerSubsidy(rs.getDouble("owner_subsidy"));
				shiftReport.setBailmentFee(rs.getDouble("bailment_fee"));
				shiftReport.setPaperVoucher(rs.getDouble("paper_voucher"));
				shiftReport.setFuelReceipt(rs.getDouble("fuel_receipt"));
				shiftReport.setOnlineReceipt(rs.getDouble("online_receipt"));
				shiftReport.setAccountVoucher(rs.getDouble("account_voucher"));
				shiftReport.setDriverRevenue(rs.getDouble("driver_revenue"));
				shiftReport.setDriverSubsidy(rs.getDouble("driver_subsidy"));
				shiftReport.setTotal(rs.getDouble("total"));
				return shiftReport;
			}
		});
		return shiftReports;
	}

	@Override
	public ShiftReport getShiftReport(int id) {

		StringBuffer query = new StringBuffer("SELECT shift_report.id, report_date, finish_date, ")
				.append("shift_report.car_id as car_id, car.rego as car_rego, car.make as car_make, ")
				.append("shift_report.driver_id as driver_id,  driver.family_name as driver_family_name, ")
				.append("driver.given_name as driver_given_name, driver.dc as driver_dc, driver.abn as driver_abn, ")
				.append("start_meter_reading, end_meter_reading, ")
				.append("meter_rev, owner_rev, owner_subsidy, bailment_fee, ")
				.append("paper_voucher, fuel_receipt, online_receipt, ")
				.append("account_voucher, driver_revenue, driver_subsidy, ")
				.append("total FROM shift_report ")
				.append("INNER JOIN car ON shift_report.car_id = car.id ")
				.append("INNER JOIN driver ON shift_report.driver_id = driver.id ")
		        .append("WHERE shift_report.id=?");

		ShiftReport shiftReport = this.jdbcTemplate.queryForObject(query.toString(), new Object[] { id },
				new RowMapper<ShiftReport>() {
					public ShiftReport mapRow(ResultSet rs, int rowNum) throws SQLException {
						ShiftReport shiftReport = new ShiftReport();
						shiftReport.setId(rs.getInt("id"));
						shiftReport.setDate(rs.getTimestamp("report_date"));
						shiftReport.setFinishDate(rs.getTimestamp("finish_date"));
						Car car = new Car();
						car.setId(rs.getInt("car_id"));
						car.setRego(rs.getString("car_rego"));
						car.setMake(rs.getString("car_make"));
						shiftReport.setCar(car);
						Driver driver = new Driver();
						driver.setId(rs.getInt("driver_id"));
						driver.setFamilyName(rs.getString("driver_family_name"));
						driver.setGivenName(rs.getString("driver_given_name"));
						driver.setDc(rs.getString("driver_dc"));
						driver.setABN(rs.getString("driver_abn"));
						shiftReport.setDriver(driver);
						shiftReport.setStartMeterReading(rs.getInt("start_meter_reading"));
						shiftReport.setEndMeterReading(rs.getInt("end_meter_reading"));
						shiftReport.setMeterRevenue(rs.getDouble("meter_rev"));
						shiftReport.setOwnerRevenue(rs.getDouble("owner_rev"));
						shiftReport.setOwnerSubsidy(rs.getDouble("owner_subsidy"));
						shiftReport.setBailmentFee(rs.getDouble("bailment_fee"));
						shiftReport.setPaperVoucher(rs.getDouble("paper_voucher"));
						shiftReport.setFuelReceipt(rs.getDouble("fuel_receipt"));
						shiftReport.setOnlineReceipt(rs.getDouble("online_receipt"));
						shiftReport.setAccountVoucher(rs.getDouble("account_voucher"));
						shiftReport.setDriverRevenue(rs.getDouble("driver_revenue"));
						shiftReport.setDriverSubsidy(rs.getDouble("driver_subsidy"));
						shiftReport.setTotal(rs.getDouble("total"));
						return shiftReport;
					}
				});
		return shiftReport;
	}

}
