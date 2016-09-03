package com.vipinstraders.taxi.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vipinstraders.taxi.domain.ExpenseType;
import com.vipinstraders.taxi.service.admin.ExpenseTypeService;

@Controller
public class AdminExpenseController {

	private ExpenseTypeService expenseTypeService;

	@Autowired
	public AdminExpenseController(ExpenseTypeService expenseTypeService) {
		this.expenseTypeService = expenseTypeService;
	}

	@RequestMapping("/adminExpenseType")
	public String showAdminIncomePage(Model model) {
		List<ExpenseType> expenseTypeList = expenseTypeService.getAll();
		if (null != expenseTypeList) {
			model.addAttribute("expenseTypeList", expenseTypeList);
		}
		return "adminExpense";
	}

	@RequestMapping("/addExpenseType")
	public String saveCar(HttpServletRequest request, Model model) {
		ExpenseType expenseType = new ExpenseType(request.getParameter("expenseName"), request.getParameter("expenseDesc"));
		expenseTypeService.addExpenseType(expenseType);

		List<ExpenseType> expenseTypeList = expenseTypeService.getAll();
		if (null != expenseTypeList) {
			model.addAttribute("expenseTypeList", expenseTypeList);
		}

		return "adminExpense";
	}

	@RequestMapping("/deleteExpenseType")
	public String deleteCar(HttpServletRequest request, Model model) {
		expenseTypeService.deleteExpenseType(request.getParameter("id"));

		List<ExpenseType> expenseTypeList = expenseTypeService.getAll();
		if (null != expenseTypeList) {
			model.addAttribute("expenseTypeList", expenseTypeList);
		}

		return "adminExpense";
	}

	@RequestMapping("/editExpenseType")
	public String editCar(HttpServletRequest request, Model model) {
		ExpenseType expenseType = new ExpenseType(Integer.parseInt(request.getParameter("expenseTypeId")),
				request.getParameter("editExpenseName"), request.getParameter("editExpenseDesc"));

		expenseTypeService.editExpenseType(expenseType);

		List<ExpenseType> expenseTypeList = expenseTypeService.getAll();
		if (null != expenseTypeList) {
			model.addAttribute("expenseTypeList", expenseTypeList);
		}

		return "adminExpense";
	}

}
