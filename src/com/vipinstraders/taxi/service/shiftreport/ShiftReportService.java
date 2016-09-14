package com.vipinstraders.taxi.service.shiftreport;

import java.util.List;

import com.vipinstraders.taxi.domain.ShiftReport;
import com.vipinstraders.taxi.object.criteria.ShiftReportSearchCriteria;

public interface ShiftReportService {

	public void add(ShiftReport shiftReport);

	public void update(ShiftReport shiftReport);

	public void delete(int id);

	public List<ShiftReport> getShiftReport(ShiftReportSearchCriteria searchCriteria);
	
	public ShiftReport getShiftReport(int id);

}
