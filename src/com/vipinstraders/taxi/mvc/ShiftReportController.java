package com.vipinstraders.taxi.mvc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vipinstraders.taxi.domain.ShiftReport;
import com.vipinstraders.taxi.service.admin.ShiftReportService;

@Controller
public class ShiftReportController {
	
	private ShiftReportService service;
	
	@Autowired
	public ShiftReportController(ShiftReportService service) {
		this.service = service;
	}

	@RequestMapping("/shiftReport")
	public String showShiftReportPage(Model model) {

		return "shiftReport";
	}

	@RequestMapping("/showAddNewShiftReportPage")
	public String showAddNewShiftReportPage(Model model) {

		return "showAddNewShiftReportPage";
	}

	@RequestMapping("/addShiftReport")
	public String saveShiftReport(HttpServletRequest request, Model modal) {
		ShiftReport shiftReport = readInputValues(request);
		service.add(shiftReport);
		return "shiftReport";
	}

	private ShiftReport readInputValues(HttpServletRequest request) {
		ShiftReport shiftReport = new ShiftReport();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		try {
			shiftReport.setDate(dateFormat.parse(request.getParameter("startDate")) );
			shiftReport.setFinishDate(dateFormat.parse(request.getParameter("finishDate")));
		} catch (ParseException e) {
			e.printStackTrace();
			shiftReport.setDate(new Date());
			shiftReport.setDate(new Date());
		}
		
		if (request.getParameter("meterRevenue") != null && request.getParameter("meterRevenue").length() > 0) {
		    shiftReport.setMeterRevenue(Double.parseDouble(request.getParameter("meterRevenue")));
		}
		if (request.getParameter("ownerRevenue") != null && request.getParameter("ownerRevenue").length() > 0 ) {
		    shiftReport.setOwnerRevenue(Double.parseDouble(request.getParameter("ownerRevenue")));
		}
		if (request.getParameter("subsidy") != null  && request.getParameter("subsidy").length() > 0 ) {
			shiftReport.setOwnerSubsidy(Double.parseDouble(request.getParameter("subsidy")));
		}
		if (request.getParameter("bailmentFee") != null && request.getParameter("bailmentFee").length() > 0) {
			shiftReport.setBailmentFee(Double.parseDouble(request.getParameter("bailmentFee")));
		}
		if (request.getParameter("paperVoucher") != null && request.getParameter("paperVoucher").length() > 0) {
			shiftReport.setPaperVoucher(Double.parseDouble(request.getParameter("paperVoucher")));
		}
		if (request.getParameter("onlineReceipt") != null && request.getParameter("onlineReceipt").length() > 0) {
			shiftReport.setOnlineReceipt(Double.parseDouble(request.getParameter("onlineReceipt")));
		}
		if (request.getParameter("fuelReceipt") != null && request.getParameter("fuelReceipt").length() > 0) {
			shiftReport.setFuelReceipt(Double.parseDouble(request.getParameter("fuelReceipt")));
		}
		if (request.getParameter("total") != null) {
			shiftReport.setTotal(Double.parseDouble(request.getParameter("total")));
		}
		
		return shiftReport;
	}

}
