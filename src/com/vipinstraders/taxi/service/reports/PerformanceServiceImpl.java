package com.vipinstraders.taxi.service.reports;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vipinstraders.taxi.domain.Performance;
import com.vipinstraders.taxi.domain.PerformanceConsidated;
import com.vipinstraders.taxi.domain.ShiftReport;
import com.vipinstraders.taxi.domain.Transaction;
import com.vipinstraders.taxi.object.criteria.PerformanceSearchCriteria;
import com.vipinstraders.taxi.object.criteria.ShiftReportSearchCriteria;
import com.vipinstraders.taxi.object.criteria.TransactionSearchCriteria;
import com.vipinstraders.taxi.service.shiftreport.ShiftReportService;
import com.vipinstraders.taxi.service.transaction.ExpenseService;

@Component
public class PerformanceServiceImpl implements PerformanceService {

	private ShiftReportService shiftReportService;
	private ExpenseService expenseService;

	@Autowired
	public PerformanceServiceImpl(ShiftReportService shiftReportService, ExpenseService expenseService) {
		this.shiftReportService = shiftReportService;
		this.expenseService = expenseService;
	}

	@Override
	public List<Performance> getPerformaceDetails(PerformanceSearchCriteria criteria) {
		return getPerformanceReport(criteria);
	}

	@Override
	public PerformanceConsidated getPerformanceConsolidatedDetails(PerformanceSearchCriteria criteria) {
		List<Performance> performanceReport = getPerformanceReport(criteria);
		double totalDriverFee = 0.00;
		double totalFuelCoast = 0.00;
		double totalOwnerCoast = 0.00;
		double totalMaintanenceCoast = 0.00;
		double meterRev = 0.00;
		double totalOwnerIncomeAfterFuelCost = 0.00;
		
		for (Performance performance : performanceReport) {
			totalDriverFee +=  performance.getDriverFees();
			totalFuelCoast += performance.getFuelCost();
			totalOwnerCoast += performance.getOwnerIncome();
			totalMaintanenceCoast += performance.getMaintanenceCost();
			meterRev += performance.getMeterRevenue();
			totalOwnerIncomeAfterFuelCost += performance.getTotal();
		}
		double totalEarnings = totalOwnerIncomeAfterFuelCost - totalMaintanenceCoast;
		PerformanceConsidated performanceConsidated = new PerformanceConsidated();
		performanceConsidated.setStartDate(criteria.getStartDate());
		performanceConsidated.setEndDate(criteria.getEndDate());
		performanceConsidated.setDriverFees(totalDriverFee);
		performanceConsidated.setFuelCost(totalFuelCoast);
		performanceConsidated.setMaintanenceCost(totalMaintanenceCoast);
		performanceConsidated.setOwnerIncome(totalOwnerCoast);
		
		performanceConsidated.setMeterRevenue(meterRev);
		performanceConsidated.setTotalEarnings(totalEarnings);
		return performanceConsidated;
	}

	private ShiftReportSearchCriteria getShiftReportSearchCriteria(PerformanceSearchCriteria criteria) {
		ShiftReportSearchCriteria shiftReportSearchCriteria = null;
		if (criteria != null) {
			shiftReportSearchCriteria = new ShiftReportSearchCriteria();
			shiftReportSearchCriteria.setStartDate(criteria.getStartDate());
			shiftReportSearchCriteria.setEndDate(criteria.getEndDate());
		}
		return shiftReportSearchCriteria;
	}

	private TransactionSearchCriteria getTransactionSearchCriteria(PerformanceSearchCriteria criteria) {
		TransactionSearchCriteria transactionSearchCriteria = null;
		if (criteria != null) {
			transactionSearchCriteria = new TransactionSearchCriteria();
			transactionSearchCriteria.setStartDate(criteria.getStartDate());
			transactionSearchCriteria.setEndDate(criteria.getEndDate());
		}
		return transactionSearchCriteria;
	}
	
	private List<Performance> getPerformanceReport(PerformanceSearchCriteria criteria) {
		List<Performance> performanceList = new ArrayList<>();
		addShiftReportDataToPerformanceList(performanceList, criteria);
		addExpensesToPerformanceList(performanceList, criteria);
		Collections.sort(performanceList);
		return performanceList;
	}
	
	private void addShiftReportDataToPerformanceList(List<Performance> performanceList, PerformanceSearchCriteria criteria) {
		if (getShiftReportSearchCriteria(criteria) == null)
			return;
		
		List<ShiftReport> shiftReportList = shiftReportService.getShiftReport(getShiftReportSearchCriteria(criteria));
		for (ShiftReport shiftReport : shiftReportList) {
			Performance performance = new Performance();
			performance.setDate(shiftReport.getDate());
			performance.setDriverFees(shiftReport.getDriverRevenue());
			performance.setDriverName(shiftReport.getDriver().getFamilyName() + " " + shiftReport.getDriver().getGivenName());
			performance.setFuelCost(shiftReport.getFuelReceipt());
			performance.setOwnerIncome(shiftReport.getOwnerRevenue());
			performance.setMeterRevenue(shiftReport.getMeterRevenue());
			performance.setTotal(shiftReport.getTotal());
			
			performanceList.add(performance);
		}
	}
	
	private void addExpensesToPerformanceList(List<Performance> performanceList, PerformanceSearchCriteria criteria) {
		if (getTransactionSearchCriteria(criteria) == null)
			return;
				
		List<Transaction> allExpenses = expenseService.getAllExpenses(getTransactionSearchCriteria(criteria));
		for (Transaction transaction : allExpenses) {
			Performance performance = new Performance();
			performance.setDate(transaction.getDate());
			performance.setMaintanenceCost(transaction.getExpenseAmount());
			performance.setMaintanenceDescription(transaction.getDescription());
			performance.setMaintanenceType(transaction.getExpenseType().getName());
			performance.setMeterReading(transaction.getMeterReading());
			performanceList.add(performance);
		}
		
	}

}
