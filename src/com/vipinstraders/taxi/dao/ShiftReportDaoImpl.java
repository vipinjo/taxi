package com.vipinstraders.taxi.dao;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import com.vipinstraders.taxi.domain.ShiftReport;

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
        .usingColumns("report_date", "finish_date", "meter_rev", "owner_subsidy", "bailment_fee", "paper_voucher", "fuel_receipt", "online_receipt", "total")
        .usingGeneratedKeyColumns("id");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("report_date", startDate);
        parameters.put("finish_date", finishDate);
        parameters.put("meter_rev", shiftReport.getMeterRevenue());
        parameters.put("owner_subsidy", shiftReport.getOwnerSubsidy());
        parameters.put("bailment_fee", shiftReport.getBailmentFee());
        parameters.put("paper_voucher", shiftReport.getPaperVoucher());
        parameters.put("fuel_receipt", shiftReport.getFuelReceipt());
        parameters.put("online_receipt",shiftReport.getOnlineReceipt());
        parameters.put("total", shiftReport.getTotal());
        Number newId = insertShiftReport.executeAndReturnKey(parameters);
		
//		this.jdbcTemplate.update("insert into shift_report  (report_date, finish_date, meter_rev, "
//				+ "owner_subsidy, bailment_fee, paper_voucher, fuel_receipt, " + "online_receipt, total) values ("
//				+ startDate + "," + finishDate + ", "
//				+ shiftReport.getMeterRevenue() + ", " + shiftReport.getOwnerSubsidy() + ", "
//				+ shiftReport.getBailmentFee() + ", " + shiftReport.getPaperVoucher() + " , "
//				+ shiftReport.getFuelReceipt() + ", " + shiftReport.getOnlineReceipt() + ", " + shiftReport.getTotal()
//				+ " )");

	}

	@Override
	public void edit(ShiftReport shiftReport) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(ShiftReport shiftReport) {
		// TODO Auto-generated method stub

	}

}
