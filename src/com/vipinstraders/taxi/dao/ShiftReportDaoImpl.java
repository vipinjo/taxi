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
        .usingColumns("report_date", "finish_date", "meter_rev", 
        		"owner_rev", "owner_subsidy", "bailment_fee", "paper_voucher", 
        		"fuel_receipt", "online_receipt", "total")
        .usingGeneratedKeyColumns("id");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("report_date", startDate);
        parameters.put("finish_date", finishDate);
        parameters.put("meter_rev", shiftReport.getMeterRevenue());
        parameters.put("owner_rev", shiftReport.getOwnerRevenue());
        parameters.put("owner_subsidy", shiftReport.getOwnerSubsidy());
        parameters.put("bailment_fee", shiftReport.getBailmentFee());
        parameters.put("paper_voucher", shiftReport.getPaperVoucher());
        parameters.put("fuel_receipt", shiftReport.getFuelReceipt());
        parameters.put("online_receipt",shiftReport.getOnlineReceipt());
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
				.append("meter_rev=?, owner_rev=?, owner_subsidy=?, bailment_fee=?, ")
				.append("paper_voucher=?, fuel_receipt=?, online_receipt=?, total=? where id=?");
		
        jdbcTemplate.update(sql.toString(), new Object[]{shiftReport.getDate(), shiftReport.getFinishDate(),
        		shiftReport.getMeterRevenue(), shiftReport.getOwnerRevenue(), shiftReport.getOwnerSubsidy(),
        		shiftReport.getBailmentFee(), shiftReport.getPaperVoucher(), shiftReport.getFuelReceipt(), shiftReport.getOnlineReceipt(),
        		shiftReport.getTotal(), shiftReport.getId()});
	}

	@Override
	public void delete(ShiftReport shiftReport) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ShiftReport> getShiftReport(ShiftReportSearchCriteria searchCriteria) {

		StringBuffer query = new StringBuffer("select id, report_date, finish_date, car_id, ")
				.append("driver_id, meter_rev, owner_rev, owner_subsidy, bailment_fee, ")
				.append("paper_voucher, fuel_receipt, online_receipt, total from shift_report ");
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
				shiftReport.setCarId(rs.getInt("car_id"));
				shiftReport.setDriverId(rs.getInt("driver_id"));
				shiftReport.setMeterRevenue(rs.getDouble("meter_rev"));
				shiftReport.setOwnerRevenue(rs.getDouble("owner_rev"));
				shiftReport.setOwnerSubsidy(rs.getDouble("owner_subsidy"));
				shiftReport.setBailmentFee(rs.getDouble("bailment_fee"));
				shiftReport.setPaperVoucher(rs.getDouble("paper_voucher"));
				shiftReport.setFuelReceipt(rs.getDouble("fuel_receipt"));
				shiftReport.setOnlineReceipt(rs.getDouble("online_receipt"));
				shiftReport.setTotal(rs.getDouble("total"));
				return shiftReport;
			}
		});
		return shiftReports;
	}

	@Override
	public ShiftReport getShiftReport(int id) {

		StringBuffer query = new StringBuffer("select id, report_date, finish_date, car_id, ")
				.append("driver_id, meter_rev, owner_rev, owner_subsidy, bailment_fee, ")
				.append("paper_voucher, fuel_receipt, online_receipt, total from shift_report where id=?");

		ShiftReport shiftReport = this.jdbcTemplate.queryForObject(query.toString(), new Object[] { id },
				new RowMapper<ShiftReport>() {
					public ShiftReport mapRow(ResultSet rs, int rowNum) throws SQLException {
						ShiftReport shiftReport = new ShiftReport();
						shiftReport.setId(rs.getInt("id"));
						shiftReport.setDate(rs.getTimestamp("report_date"));
						shiftReport.setFinishDate(rs.getTimestamp("finish_date"));
						shiftReport.setCarId(rs.getInt("car_id"));
						shiftReport.setDriverId(rs.getInt("driver_id"));
						shiftReport.setMeterRevenue(rs.getDouble("meter_rev"));
						shiftReport.setOwnerRevenue(rs.getDouble("owner_rev"));
						shiftReport.setOwnerSubsidy(rs.getDouble("owner_subsidy"));
						shiftReport.setBailmentFee(rs.getDouble("bailment_fee"));
						shiftReport.setPaperVoucher(rs.getDouble("paper_voucher"));
						shiftReport.setFuelReceipt(rs.getDouble("fuel_receipt"));
						shiftReport.setOnlineReceipt(rs.getDouble("online_receipt"));
						shiftReport.setTotal(rs.getDouble("total"));
						return shiftReport;
					}
				});
		return shiftReport;
	}

}
