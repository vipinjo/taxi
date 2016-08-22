package com.vipinstraders.taxi.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vipinstraders.taxi.domain.Car;
import com.vipinstraders.taxi.service.CarService;

@Controller
public class CarController {
	
	private CarService carService;
	
	@Autowired
	public CarController(CarService carService) {
		this.carService = carService;
	}
	
	@RequestMapping("/addCar")
	public String showPage(HttpServletRequest request) {
		 Car car = new Car();
		 car.setMake(request.getParameter("carMake"));
		 car.setRego(request.getParameter("rego"));
		carService.addCar(car);
		return "admin-page";
	}
	
	

}
