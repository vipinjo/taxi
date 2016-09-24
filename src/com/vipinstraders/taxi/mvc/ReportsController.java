
package com.vipinstraders.taxi.mvc;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.vipinstraders.taxi.domain.Driver;
import com.vipinstraders.taxi.domain.ShiftReport;
import com.vipinstraders.taxi.domain.ShiftReportDetails;
import com.vipinstraders.taxi.object.criteria.ShiftReportSearchCriteria;
import com.vipinstraders.taxi.service.admin.DriverService;
import com.vipinstraders.taxi.service.print.PrintService;
import com.vipinstraders.taxi.service.shiftreport.ShiftReportService;
import com.vipinstraders.taxi.util.ReportUtils;

@Controller
public class ReportsController {

	private ShiftReportService shiftReportService;
	private DriverService driverService;
	private PrintService printService;

	@Autowired
	public ReportsController(ShiftReportService shiftReportService, DriverService driverService,
			PrintService printService) {
		this.shiftReportService = shiftReportService;
		this.driverService = driverService;
		this.printService = printService;
	}

	@RequestMapping("/reports")
	public String showShiftReportPage(Model model) {
		List<Driver> driverList = driverService.getAllDrivers();
		model.addAttribute("driverList", driverList);
		return "reportsShiftReport";
	}

	@RequestMapping("/reportsShiftReports")
	public String searchShiftReport(HttpServletRequest request, Model model) {

		int page = 1;
		int recordsPerPage = 10;
		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));

		ShiftReportSearchCriteria searchCriteria = getSearchCriteria(request);
		List<Driver> driverList = driverService.getAllDrivers();
		model.addAttribute("driverList", driverList);
		List<ShiftReport> shiftReportList = null;
		ShiftReportDetails shiftReportDetails = null;
		List<ShiftReport> subListTosShowInPage = null;
		if (searchCriteria.getStartDate() != null) {
			shiftReportList = shiftReportService.getShiftReport(searchCriteria);
			if (shiftReportList != null && !shiftReportList.isEmpty()) {
				shiftReportDetails = shiftReportService.getShiftReportDetails(shiftReportList);
				int noOfRecords = shiftReportList.size();
				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
				int fromIndex = (page - 1) * recordsPerPage;
				int toIndex = (page - 1) + recordsPerPage;
				if (toIndex >= shiftReportList.size())
					toIndex = shiftReportList.size();
				subListTosShowInPage = shiftReportList.subList(fromIndex, toIndex);
				request.setAttribute("noOfPages", noOfPages);
				request.setAttribute("currentPage", page);
			}
			model.addAttribute("shiftReportList", subListTosShowInPage);
			model.addAttribute("shiftReportDetails", shiftReportDetails);
			model.addAttribute("searchCriteria", searchCriteria);
		}
		model.addAttribute("displayText",
				ReportUtils.getDisplayTextBasedOnShiftReport(searchCriteria, "Shift Report", shiftReportList));

		return "reportsShiftReport";
	}

	@RequestMapping("/performanceReport")
	public String showPerformanceReportPage(Model model) {

		return "performanceReport";
	}

	@RequestMapping("/downloadShiftReportfiles")
	public void getFile(HttpServletRequest request, HttpServletResponse response) {
		ShiftReportSearchCriteria searchCriteria = getSearchCriteria(request);
		List<ShiftReport> shiftReportList = shiftReportService.getShiftReport(searchCriteria);
		ShiftReportDetails shiftReportDetails = shiftReportService.getShiftReportDetails(shiftReportList);
		if (shiftReportList != null && shiftReportList.size() > 0) {
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition",
					"attachment;filename=shiftReport_" + ReportUtils.getReportPrintTime() + ".pdf");
			try {
				printService.printShiftReport(searchCriteria, shiftReportDetails, shiftReportList,
						response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

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

		if (hasValue(request, "driver")) {
			criteria.setDriverId(Integer.parseInt(request.getParameter("driver")));
		}
		return criteria;
	}

	private boolean hasValue(HttpServletRequest request, String parameter) {
		return request != null && request.getParameter(parameter) != null && !request.getParameter(parameter).isEmpty();
	}

}
