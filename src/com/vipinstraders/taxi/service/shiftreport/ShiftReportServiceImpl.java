package com.vipinstraders.taxi.service.shiftreport;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vipinstraders.taxi.dao.ShiftReportDao;
import com.vipinstraders.taxi.domain.ShiftReport;
import com.vipinstraders.taxi.domain.ShiftReportDetails;
import com.vipinstraders.taxi.object.criteria.ShiftReportSearchCriteria;

@Component
public class ShiftReportServiceImpl implements ShiftReportService {

	private ShiftReportDao dao;
	
	@Autowired
	public ShiftReportServiceImpl(ShiftReportDao dao) {
		this.dao = dao;
	}
	
	@Override
	public void add(ShiftReport shiftReport) {
		dao.add(shiftReport);

	}

	@Override
	public void update(ShiftReport shiftReport) {
		dao.update(shiftReport);

	}

	@Override
	public void delete(int id) {
		dao.delete(id);
	}

	@Override
	public List<ShiftReport> getShiftReport(ShiftReportSearchCriteria searchCriteria) {
		return dao.getShiftReport(searchCriteria);
	}

	@Override
	public ShiftReport getShiftReport(int id) {
		return dao.getShiftReport(id);
	}
	
    public ShiftReportDetails getShiftReportDetails(List<ShiftReport> shiftReportList) {
		
		if (shiftReportList == null || shiftReportList.isEmpty())
			return null;
		ShiftReportDetails details = new ShiftReportDetails();
		details.setAccountVoucher(calculateTotalAccountVoucher(shiftReportList));
		details.setFuelRecipts(calculatFuelRecipts(shiftReportList));
		details.setOnlineReceipts(calculateOnlineReceipts(shiftReportList));
		details.setPaperVoucher(calculateTotalPaperVoucher(shiftReportList));
		details.setTotal(calculateTotal(shiftReportList));
		details.setTotalDriverRevenue(calculateTotalDriverRevenue(shiftReportList));
		details.setTotalMeterRevenue(calculateTotalMeterRevenue(shiftReportList));
		details.setTotolOwnerRevenue(calculateTotalOwnerRevenue(shiftReportList));
		
		return details;
	}
	
	private double calculateTotalMeterRevenue(List<ShiftReport> shiftReportList) {
		double totalMeterRevenue =  0.00;
		for (ShiftReport shiftReport : shiftReportList) {
			totalMeterRevenue += shiftReport.getMeterRevenue();
		}
		return totalMeterRevenue;
	}
	
	private double calculateTotalOwnerRevenue(List<ShiftReport> shiftReportList) {
		double totalOwnerRevenue =  0.00;
		for (ShiftReport shiftReport : shiftReportList) {
			totalOwnerRevenue += shiftReport.getOwnerRevenue();
		}
		return totalOwnerRevenue;
	}
	
	private double calculateTotalPaperVoucher(List<ShiftReport> shiftReportList) {
		double totalPaperVoucher =  0.00;
		for (ShiftReport shiftReport : shiftReportList) {
			totalPaperVoucher += shiftReport.getPaperVoucher();
		}
		return totalPaperVoucher;
	}
	
	private double calculateOnlineReceipts(List<ShiftReport> shiftReportList) {
		double totalOnlineReceipt =  0.00;
		for (ShiftReport shiftReport : shiftReportList) {
			totalOnlineReceipt += shiftReport.getOnlineReceipt();
		}
		return totalOnlineReceipt;
	}
	
	private double calculatFuelRecipts(List<ShiftReport> shiftReportList) {
		double totalFuelReceipt =  0.00;
		for (ShiftReport shiftReport : shiftReportList) {
			totalFuelReceipt += shiftReport.getFuelReceipt();
		}
		return totalFuelReceipt;
	}
	
	private double calculateTotalAccountVoucher(List<ShiftReport> shiftReportList) {
		double totalAccountVoucher =  0.00;
		for (ShiftReport shiftReport : shiftReportList) {
			totalAccountVoucher += shiftReport.getAccountVoucher();
		}
		return totalAccountVoucher;
	}
	
	private double calculateTotalDriverRevenue(List<ShiftReport> shiftReportList) {
		double totalDriverRev = 0.00;
		for (ShiftReport shiftReport : shiftReportList) {
			totalDriverRev += shiftReport.getDriverRevenue();
		}
		return totalDriverRev;
	}
	
	private double calculateTotal(List<ShiftReport> shiftReportList) {
		double total =  0.00;
		for (ShiftReport shiftReport : shiftReportList) {
			total += shiftReport.getTotal();
		}
		return total;
	}

}
