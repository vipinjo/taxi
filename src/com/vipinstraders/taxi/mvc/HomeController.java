package com.vipinstraders.taxi.mvc;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vipinstraders.taxi.domain.PerformanceConsidated;
import com.vipinstraders.taxi.object.criteria.PerformanceSearchCriteria;
import com.vipinstraders.taxi.service.reports.PerformanceService;

@Controller
public class HomeController {
	
	private PerformanceService performanceService;
	
	@Autowired
	public HomeController(PerformanceService performanceService) {
		this.performanceService = performanceService;
	}
	
	@RequestMapping({"/", "", "home"})
	public String showPage(HttpServletRequest request, Model model) {
		model.addAttribute("thisWeek", getPerformanceDataForThisWeek());
		model.addAttribute("thisMonth", getPerformanceDataForThisMonth());
		model.addAttribute("thisYear", getPerformanceDataForThisYear());
		
		return "home";
	}
	
	private PerformanceConsidated getPerformanceDataForThisYear() {
		PerformanceSearchCriteria criteria = new PerformanceSearchCriteria();
		criteria.setStartDate(getThisFinanceYearStartDate());
		return performanceService.getPerformanceConsolidatedDetails(criteria);
	    
	}
	
	private PerformanceConsidated getPerformanceDataForThisMonth() {
		PerformanceSearchCriteria criteria = new PerformanceSearchCriteria();
		criteria.setStartDate(getThisMonthStartDate());
		return performanceService.getPerformanceConsolidatedDetails(criteria);
	}
	
	private PerformanceConsidated getPerformanceDataForThisWeek() {
		PerformanceSearchCriteria criteria = new PerformanceSearchCriteria();
		criteria.setStartDate(getThisWeekStartDate());
		return performanceService.getPerformanceConsolidatedDetails(criteria);
	}
	
	private Date getThisFinanceYearStartDate() {
	   Calendar cal = Calendar.getInstance();
	   if (cal.get(Calendar.MONTH) >= Calendar.JULY) {
		   cal.set(Calendar.DAY_OF_MONTH, 1);
		   cal.set(Calendar.MONTH, Calendar.JULY);
	   } else {
		   cal.set(Calendar.DAY_OF_MONTH, 1);
		   cal.set(Calendar.MONTH, Calendar.JULY);
		   cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 1);
	   }
	   setTimeToDayStartTime(cal);
	   
	   return new Date(cal.getTimeInMillis());
	}
	
	private Date getThisWeekStartDate() {
		Calendar cal = Calendar.getInstance();   // this takes current date
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		setTimeToDayStartTime(cal);
	    return new Date(cal.getTimeInMillis());
	}
	
	private Date getThisMonthStartDate() {
		Calendar cal = Calendar.getInstance();   // this takes current date
		cal.set(Calendar.DAY_OF_MONTH, 1);
		setTimeToDayStartTime(cal);
		return new Date(cal.getTimeInMillis());
	}
	
	private void setTimeToDayStartTime(Calendar cal) {
		if (cal == null)
			return;
		cal.set(Calendar.HOUR_OF_DAY, 00);
		cal.set(Calendar.MINUTE, 00);
		cal.set(Calendar.SECOND, 00);
		cal.set(Calendar.MILLISECOND, 00);
	}
	
	

}
