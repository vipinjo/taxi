package com.vipinstraders.taxi.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	

}
