package com.vipinstraders.taxi.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vipinstraders.taxi.domain.Car;
import com.vipinstraders.taxi.service.admin.CarService;

@Controller
public class CarController {
	
	private CarService carService;
	
	@Autowired
	public CarController(CarService carService) {
		this.carService = carService;
	}
	
	@RequestMapping("/addCar")
	public String saveCar(HttpServletRequest request, Model model) {
		 Car car = new Car();
		 car.setMake(request.getParameter("carMake"));
		 car.setRego(request.getParameter("rego"));
		 carService.addCar(car);
		 
		 List<Car> carList = carService.getAllCars();
	        if (null != carList) {
	        	model.addAttribute("carList", carList);
	        }
		 
		return "admin";
	}
	
	@RequestMapping("/deleteCar")
	public String deleteCar(HttpServletRequest request, Model model) {
		 
		 carService.deleteCar(request.getParameter("id"));
		 
		 List<Car> carList = carService.getAllCars();
	        if (null != carList) {
	        	model.addAttribute("carList", carList);
	        }
		 
		return "admin";
	}
	
	@RequestMapping("/editCar")
	public String editCar(HttpServletRequest request, Model model) {
		
		 Car car = new Car();
		 
		 car.setId(Integer.parseInt(request.getParameter("carId")));
		 car.setMake(request.getParameter("editCarMake"));
		 car.setRego(request.getParameter("editRego"));
		 
		 carService.editCar(car);
		 
		 List<Car> carList = carService.getAllCars();
	        if (null != carList) {
	        	model.addAttribute("carList", carList);
	        }
		 
		return "admin";
	}
	
	

}
