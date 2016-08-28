package com.vipinstraders.taxi.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vipinstraders.taxi.domain.Car;
import com.vipinstraders.taxi.service.CarService;

@Controller
public class AdminController {

	private CarService carService;

	@Autowired
	public AdminController(CarService carService) {
		this.carService = carService;
	}

	@RequestMapping("/admin")
	public String showPage(Model model) {
		model.addAttribute("mainMenu", "admin");
		List<Car> carList = carService.getAllCars();
        if (null != carList) {
        	model.addAttribute("carList", carList);
        }
		return "admin";
	}

}
