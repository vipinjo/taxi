
package com.vipinstraders.taxi.mvc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vipinstraders.taxi.domain.Driver;
import com.vipinstraders.taxi.domain.ShiftReport;
import com.vipinstraders.taxi.domain.ShiftReportDetails;
import com.vipinstraders.taxi.object.criteria.ShiftReportSearchCriteria;
import com.vipinstraders.taxi.service.admin.DriverService;
import com.vipinstraders.taxi.service.shiftreport.ShiftReportService;

@Controller
public class ReportsController {
	
	private ShiftReportService shiftReportService;
	private DriverService driverService;
	
	@Autowired
	public ReportsController(ShiftReportService shiftReportService, DriverService driverService) {
		this.shiftReportService = shiftReportService;
		this.driverService = driverService;
	}
	
	@RequestMapping("/reports")
	public String showShiftReportPage(Model model) {
		List<Driver> allDrivers = driverService.getAllDrivers(); 
		return "reportsShiftReport";
	}
	
	@RequestMapping("/reportsShiftReports")
	public String searchShiftReport(HttpServletRequest request, Model model) {
		//TODO: give back the object that consolidates the report.
		//TODO: Implement the pagination
		ShiftReportSearchCriteria searchCriteria = getSearchCriteria(request);
		if (searchCriteria.getStartDate() != null) {
			List<ShiftReport> shiftReportList = shiftReportService.getShiftReport(searchCriteria);
			ShiftReportDetails shiftReportDetails = shiftReportService.getShiftReportDetails(shiftReportList);
			model.addAttribute("shiftReportList", shiftReportList);
			model.addAttribute("shiftReportDetails", shiftReportDetails);
		}
		
		return "reportsShiftReport";
	}
	
	@RequestMapping("/performanceReport")
	public String showPerformanceReportPage(Model model) {
		
		return "performanceReport";
	}
	
	private ShiftReportSearchCriteria getSearchCriteria(HttpServletRequest request) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		ShiftReportSearchCriteria criteria = new ShiftReportSearchCriteria();
		if (hasValue(request, "startDate")) {
			try {
				criteria.setStartDate(dateFormat.parse(request.getParameter("startDate")));
			} catch (ParseException e) {
				e.printStackTrace();
				criteria.setStartDate(new Date());
			}
		}
		
		if (hasValue(request, "endDate")) {
			try {
				criteria.setEndDate(dateFormat.parse(request.getParameter("endDate")));
			} catch (ParseException e) {
				e.printStackTrace();
				criteria.setEndDate(new Date());
			}
		}
		return criteria;
	}
	
	private boolean hasValue(HttpServletRequest request, String parameter) {
		return request != null && request.getParameter(parameter) != null && !request.getParameter(parameter).isEmpty();
	}

}
