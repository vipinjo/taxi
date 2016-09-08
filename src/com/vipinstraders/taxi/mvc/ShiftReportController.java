package com.vipinstraders.taxi.mvc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vipinstraders.taxi.domain.ShiftReport;

@Controller
public class ShiftReportController {

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
		readInputValues(request);
		return "shiftReport";
	}

	private ShiftReport readInputValues(HttpServletRequest request) {
		ShiftReport shiftReport = new ShiftReport();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		try {
			shiftReport.setDate(dateFormat.parse(request.getParameter("startDate")) );
			shiftReport.setFinishDate(dateFormat.parse(request.getParameter("startDate")));
		} catch (ParseException e) {
			e.printStackTrace();
			shiftReport.setDate(new Date());
			shiftReport.setDate(new Date());
		}
		
		request.getParameter("meterRevenue");
		request.getParameter("ownerRevenue");
		request.getParameter("subsidy");
		request.getParameter("bailmentFee");
		request.getParameter("paperVoucher");
		request.getParameter("onlineReceipt");
		request.getParameter("fuelReceipt");
		request.getParameter("total");
		return shiftReport;
	}

}
