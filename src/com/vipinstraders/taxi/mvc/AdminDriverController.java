package com.vipinstraders.taxi.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vipinstraders.taxi.domain.Driver;
import com.vipinstraders.taxi.service.admin.DriverService;

@Controller
public class AdminDriverController {
	
	private DriverService service;
	
	@Autowired
	public AdminDriverController(DriverService service) {
		this.service = service;
	}
	
	@RequestMapping("/adminDriver")
	public String showAdminDriverPage(Model model) {
		List<Driver> driverList = service.getAllDrivers();
		model.addAttribute("driverList", driverList);
		return "adminDriver";
	}
	
	@RequestMapping("/addDriver")
	public String addDriver(HttpServletRequest request, Model model) {
		
		Driver driver = createDriver(request);
		service.addDriver(driver);
		List<Driver> driverList = service.getAllDrivers();
		model.addAttribute("driverList", driverList);
		
		return "adminDriver";
	}
	
	@RequestMapping("/editDriver")
	public String editDriver(HttpServletRequest request, Model model) {
		
		Driver driver = createDriverForEdit(request);
		service.editDriver(driver);
		List<Driver> driverList = service.getAllDrivers();
		model.addAttribute("driverList", driverList);
		
		return "adminDriver";
	}
	
	@RequestMapping("/deleteDriver")
	private String deleteDriver(HttpServletRequest request, Model model) {
		
		if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
		    service.deleteDriver(Integer.parseInt(request.getParameter("id")));
		}
		
		List<Driver> driverList = service.getAllDrivers();
		model.addAttribute("driverList", driverList);
		
		return "adminDriver";
		
	}
	
	private Driver createDriver(HttpServletRequest request) {

		Driver driver = new Driver();

		if (hasValue("id", request)) {
			driver.setId(Integer.parseInt(request.getParameter("id")));
		}

		if (hasValue("familyName", request)) {
			driver.setFamilyName(request.getParameter("familyName"));
		}

		if (hasValue("givenName", request)) {
			driver.setGivenName(request.getParameter("givenName"));
		}

		if (hasValue("dc", request)) {
			driver.setDc(request.getParameter("dc"));
		}

		if (hasValue("abn", request)) {
			driver.setABN(request.getParameter("abn"));
		}

		return driver;
	}
	
	private Driver createDriverForEdit(HttpServletRequest request) {

		Driver driver = new Driver();

		if (hasValue("driverId", request)) {
			driver.setId(Integer.parseInt(request.getParameter("driverId")));
		}

		if (hasValue("editFamilyName", request)) {
			driver.setFamilyName(request.getParameter("editFamilyName"));
		}

		if (hasValue("editGivenName", request)) {
			driver.setGivenName(request.getParameter("editGivenName"));
		}

		if (hasValue("editDc", request)) {
			driver.setDc(request.getParameter("editDc"));
		}

		if (hasValue("editAbn", request)) {
			driver.setABN(request.getParameter("editAbn"));
		}

		return driver;
	}
	
	private boolean hasValue(String parameterName, HttpServletRequest request) {
		if (request.getParameter(parameterName) != null && !request.getParameter(parameterName).isEmpty()) 
			return true;
		return false;
	}

}
