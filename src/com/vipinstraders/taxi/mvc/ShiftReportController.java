package com.vipinstraders.taxi.mvc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vipinstraders.taxi.domain.Car;
import com.vipinstraders.taxi.domain.Driver;
import com.vipinstraders.taxi.domain.ShiftReport;
import com.vipinstraders.taxi.object.criteria.ShiftReportSearchCriteria;
import com.vipinstraders.taxi.service.admin.CarService;
import com.vipinstraders.taxi.service.admin.DriverService;
import com.vipinstraders.taxi.service.shiftreport.ShiftReportService;

@Controller
public class ShiftReportController {
	
	private ShiftReportService service;
	private DriverService driverService;
	private CarService carService;
	
	@Autowired
	public ShiftReportController(ShiftReportService service, DriverService driverService, CarService carService) {
		this.service = service;
		this.driverService = driverService;
		this.carService = carService;
	}

	@RequestMapping("/shiftReport")
	public String showShiftReportPage(Model model) {

		return "shiftReport";
	}

	@RequestMapping("/showAddNewShiftReportPage")
	public String showAddNewShiftReportPage(Model model) {
		
		List<Driver> allDrivers = driverService.getAllDrivers();
		List<Car> allCars = carService.getAllCars();
		
		model.addAttribute("displayText", "Add New Shift Report");
		model.addAttribute("driverList", allDrivers); 
		model.addAttribute("carList", allCars); 
		model.addAttribute("action", "addShiftReport");
		return "showAddNewShiftReportPage";
	}

	@RequestMapping("/addShiftReport")
	public String saveShiftReport(HttpServletRequest request, Model modal) {
		ShiftReport shiftReport = readInputValues(request);
		service.add(shiftReport);
		return "shiftReport";
	}
	
	@RequestMapping("/editShiftReport")
	public String editShiftReport(HttpServletRequest request, Model model) {
		if (request.getParameter("id") != null) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			ShiftReportSearchCriteria searchCriteria = getSearchCriteria(request);
			ShiftReport shiftReport = service.getShiftReport(id);
			
			List<Driver> allDrivers = driverService.getAllDrivers();
			List<Car> allCars = carService.getAllCars();
			model.addAttribute("driverList", allDrivers); 
			model.addAttribute("carList", allCars); 
			
			model.addAttribute("searchCriteria", searchCriteria);
			model.addAttribute("shiftReport", shiftReport);
			model.addAttribute("action", "updateShiftReport");
			model.addAttribute("displayText", "Update Shift Report");
		}
		return "showAddNewShiftReportPage";
	}
	
	@RequestMapping("/updateShiftReport")
	public String updateShiftReport(HttpServletRequest request, Model model) {
		ShiftReport shiftReport = readInputValues(request);
		service.update(shiftReport);
		
		// show the previous search results after update the values
        ShiftReportSearchCriteria searchCriteria = getSearchCriteria(request);
		
		List<ShiftReport> shiftReportList = service.getShiftReport(searchCriteria);
		
		if (shiftReportList !=null && !shiftReportList.isEmpty()) {
		    model.addAttribute("shiftReportList", shiftReportList);
		}
		model.addAttribute("searchCriteria", searchCriteria);
		model.addAttribute("displayText", getDisplayTextBasedOnShiftReport(searchCriteria, shiftReportList));
		
		return "shiftReport";
		
	}
	
	@RequestMapping("/searchShiftReport")
	public String searchShiftReport(HttpServletRequest request, Model model) {
		ShiftReportSearchCriteria searchCriteria = getSearchCriteria(request);
		
		List<ShiftReport> shiftReportList = service.getShiftReport(searchCriteria);
		
		if (shiftReportList !=null && !shiftReportList.isEmpty()) {
		    model.addAttribute("shiftReportList", shiftReportList);
		}
		model.addAttribute("searchCriteria", searchCriteria);
		model.addAttribute("displayText", getDisplayTextBasedOnShiftReport(searchCriteria, shiftReportList));
		return "shiftReport";
	}
	
	@RequestMapping("/deleteShiftReport")
	public String deleteShiftReport(HttpServletRequest request, Model model) {
		if (request.getParameter("id") != null && request.getParameter("id").length() > 0) {
			int id = Integer.parseInt(request.getParameter("id"));
			service.delete(id);
		}
		return "shiftReport";
	}
	
	private ShiftReportSearchCriteria getSearchCriteria(HttpServletRequest request) {
		ShiftReportSearchCriteria criteria = new ShiftReportSearchCriteria();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		if (isValueAvailableInRequest(request, "startDate")) {
			try {
				criteria.setStartDate(dateFormat.parse(request.getParameter("startDate")));
			} catch (ParseException e) {
				e.printStackTrace();
				criteria.setStartDate(new Date());
			}
		}
		
		if (isValueAvailableInRequest(request, "endDate")) {
			try {
				criteria.setEndDate(dateFormat.parse(request.getParameter("endDate")));
			} catch (ParseException e) {
				e.printStackTrace();
				criteria.setEndDate(new Date());
			}
		}
		return criteria;
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
		
		if (request.getParameter("id") != null && request.getParameter("id").length() > 0) {
			shiftReport.setId(Integer.parseInt(request.getParameter("id")));
		}
		
		if (request.getParameter("driver") != null && request.getParameter("driver").length() > 0) {
			Driver driver = new Driver();
			driver.setId(Integer.parseInt(request.getParameter("driver")));
			shiftReport.setDriver(driver);
		}
		
		if (request.getParameter("car") != null && request.getParameter("car").length() > 0) {
			Car car = new Car();
			car.setId(Integer.parseInt(request.getParameter("car")));
			shiftReport.setCar(car);
		}
		
		if (request.getParameter("startMeterReading") != null && request.getParameter("startMeterReading").length() > 0) {
			shiftReport.setStartMeterReading(Integer.parseInt(request.getParameter("startMeterReading").split("\\.")[0]));
		}
		
		if (request.getParameter("endMeterReading") != null && request.getParameter("endMeterReading").length() > 0) {
			shiftReport.setEndMeterReading(Integer.parseInt(request.getParameter("endMeterReading").split("\\.")[0]));
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
		
		if (request.getParameter("accountVoucher") != null && request.getParameter("accountVoucher").length() > 0) {
			shiftReport.setAccountVoucher(Double.parseDouble(request.getParameter("accountVoucher")));
		}
		
		if (request.getParameter("fuelReceipt") != null && request.getParameter("fuelReceipt").length() > 0) {
			shiftReport.setFuelReceipt(Double.parseDouble(request.getParameter("fuelReceipt")));
		}

		if (request.getParameter("driverRevenue") != null && request.getParameter("driverRevenue").length() > 0) {
			shiftReport.setDriverRevenue(Double.parseDouble(request.getParameter("driverRevenue")));
		}

		if (request.getParameter("driverSubsidy") != null && request.getParameter("driverSubsidy").length() > 0) {
			shiftReport.setDriverSubsidy(Double.parseDouble(request.getParameter("driverSubsidy")));
		}
		
		if (request.getParameter("total") != null) {
			shiftReport.setTotal(Double.parseDouble(request.getParameter("total")));
		}
		
		return shiftReport;
	}
	
	private  boolean isValueAvailableInRequest(HttpServletRequest request, String parameter) {
		if (request.getParameter(parameter) != null && request.getParameter(parameter).length() > 0)
			return true;
		return false;
	}
	
	private String getDisplayTextBasedOnShiftReport(ShiftReportSearchCriteria criteria,
			List<ShiftReport> shiftReportList) {
		StringBuilder resultText = new StringBuilder("");

		if (shiftReportList != null && shiftReportList.isEmpty()) {
			resultText.append("No results found ");
		} else if (shiftReportList != null && !shiftReportList.isEmpty()) {
			resultText.append("Search results ");
		}
		if (criteria.getStartDate() != null && criteria.getEndDate() == null) {
			resultText.append("from ").append(formatDate(criteria.getStartDate(), "EEE, MMM d, yyyy")).append(" ");
		} else if (criteria.getStartDate() != null && criteria.getEndDate() != null) {
			resultText.append("from ").append(formatDate(criteria.getStartDate(), "EEE, MMM d, yyyy")).append(" to ")
					.append(formatDate(criteria.getEndDate(), "EEE, MMM d, yyyy"));
		}

		return resultText.toString();
	}
	
	private String formatDate(Date date, String format) {
		if (date == null)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}

}
