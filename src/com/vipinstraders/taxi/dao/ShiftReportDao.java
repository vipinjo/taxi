package com.vipinstraders.taxi.dao;

import java.util.List;

import com.vipinstraders.taxi.domain.ShiftReport;
import com.vipinstraders.taxi.object.criteria.ShiftReportSearchCriteria;

public interface ShiftReportDao {
	
	public void add(ShiftReport shiftReport);
	
	public void update(ShiftReport shiftReport);
	
	public void delete(ShiftReport shiftReport);
	
	public List<ShiftReport> getShiftReport(ShiftReportSearchCriteria searchCriteria);
	
	public ShiftReport getShiftReport(int id);

}
