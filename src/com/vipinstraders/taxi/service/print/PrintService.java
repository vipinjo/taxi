package com.vipinstraders.taxi.service.print;

import java.util.List;

import javax.servlet.ServletOutputStream;

import com.vipinstraders.taxi.domain.Performance;
import com.vipinstraders.taxi.domain.PerformanceConsidated;
import com.vipinstraders.taxi.domain.ShiftReport;
import com.vipinstraders.taxi.domain.ShiftReportDetails;
import com.vipinstraders.taxi.object.criteria.PerformanceSearchCriteria;
import com.vipinstraders.taxi.object.criteria.ShiftReportSearchCriteria;

public interface PrintService {

	public void printShiftReport(ShiftReportSearchCriteria searchCriteria, ShiftReportDetails shiftReportDetails,
			List<ShiftReport> shiftReportList, ServletOutputStream out);

	public void printPerformanceReport(ServletOutputStream out, PerformanceSearchCriteria searchCriteria,
			List<Performance> performaceDetailsList, PerformanceConsidated performanceConsolidatedDetails);

	public void printShiftReportDetails(ShiftReport shiftReport, ServletOutputStream out);

}
