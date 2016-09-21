package com.vipinstraders.taxi.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReportsController {
	
	@RequestMapping("/reports")
	public String showShiftReportPage(Model model) {

		return "reportsShiftReport";
	}
	
	@RequestMapping("/performanceReport")
	public String showPerformanceReportPage(Model model) {
		
		return "performanceReport";
	}

}
