package com.vipinstraders.taxi.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.vipinstraders.taxi.domain.ShiftReport;
import com.vipinstraders.taxi.object.criteria.ShiftReportSearchCriteria;

public class ReportUtils {
	
	
	public static String getDisplayTextBasedOnShiftReport(ShiftReportSearchCriteria criteria, String reportName,
			List<ShiftReport> shiftReportList) {
		StringBuilder resultText = new StringBuilder("");
		
		if (shiftReportList == null)
			return resultText.append("No Results Found").toString();

		if (shiftReportList != null && shiftReportList.isEmpty()) {
			resultText.append("No results found ");
		} else if (shiftReportList != null && !shiftReportList.isEmpty()) {
			if (reportName != null && !reportName.isEmpty()) {
				resultText.append(reportName ).append(" ");
			} else {
			    resultText.append("Search results ");
			}
		}
		if (criteria.getStartDate() != null && criteria.getEndDate() == null) {
			resultText.append("from ").append(formatDate(criteria.getStartDate(), "EEE, MMM d, yyyy")).append(" ");
		} else if (criteria.getStartDate() != null && criteria.getEndDate() != null) {
			resultText.append("from ").append(formatDate(criteria.getStartDate(), "EEE, MMM d, yyyy")).append(" to ")
					.append(formatDate(criteria.getEndDate(), "EEE, MMM d, yyyy"));
		}

		return resultText.toString();
	}
	
	public static String getReportPrintTime() {
		SimpleDateFormat simpleDateFormat =
	            new SimpleDateFormat("yyyyMMddhhmmss");
	    return simpleDateFormat.format(new Date());
	}
	
	private static String formatDate(Date date, String format) {
		if (date == null)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}
}
