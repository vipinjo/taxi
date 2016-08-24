package com.vipinstraders.taxi.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	
	@RequestMapping("/admin")
	public String showPage(Model model) {
		model.addAttribute("mainMenu", "admin");
		return "admin-page";
	}
	
	

}
