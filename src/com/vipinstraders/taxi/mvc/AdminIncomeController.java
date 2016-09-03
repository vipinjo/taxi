package com.vipinstraders.taxi.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vipinstraders.taxi.domain.IncomeType;
import com.vipinstraders.taxi.service.admin.IncomeTypeService;

@Controller
public class AdminIncomeController {

	private IncomeTypeService incomeTypeService;

	@Autowired
	public AdminIncomeController(IncomeTypeService incomeTypeService) {
		this.incomeTypeService = incomeTypeService;
	}

	@RequestMapping("/adminIncomeType")
	public String showAdminIncomePage(Model model) {
		List<IncomeType> incomeTypeList = incomeTypeService.getAll();
		if (null != incomeTypeList) {
			model.addAttribute("incomeTypeList", incomeTypeList);
		}
		return "adminIncome";
	}

	@RequestMapping("/addIncomeType")
	public String saveCar(HttpServletRequest request, Model model) {
		IncomeType incomeType = new IncomeType(request.getParameter("incomeName"), request.getParameter("incomeDesc"));
		incomeTypeService.addIncomeType(incomeType);

		List<IncomeType> incomeTypeList = incomeTypeService.getAll();
		if (null != incomeTypeList) {
			model.addAttribute("incomeTypeList", incomeTypeList);
		}

		return "adminIncome";
	}

	@RequestMapping("/deleteIncomeType")
	public String deleteCar(HttpServletRequest request, Model model) {
		incomeTypeService.deleteIncomeType(request.getParameter("id"));

		List<IncomeType> incomeTypeList = incomeTypeService.getAll();
		if (null != incomeTypeList) {
			model.addAttribute("incomeTypeList", incomeTypeList);
		}

		return "adminIncome";
	}

	@RequestMapping("/editIncomeType")
	public String editCar(HttpServletRequest request, Model model) {
		IncomeType incomeType = new IncomeType(Integer.parseInt(request.getParameter("incomeTypeId")),
				request.getParameter("editIncomeName"), request.getParameter("editIncomeDesc"));

		incomeTypeService.editIncomeType(incomeType);

		List<IncomeType> incomeTypeList = incomeTypeService.getAll();
		if (null != incomeTypeList) {
			model.addAttribute("incomeTypeList", incomeTypeList);
		}

		return "adminIncome";
	}

}
