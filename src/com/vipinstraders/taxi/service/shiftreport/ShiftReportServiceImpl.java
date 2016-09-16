package com.vipinstraders.taxi.service.shiftreport;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vipinstraders.taxi.dao.ShiftReportDao;
import com.vipinstraders.taxi.domain.ShiftReport;
import com.vipinstraders.taxi.object.criteria.ShiftReportSearchCriteria;

@Component
public class ShiftReportServiceImpl implements ShiftReportService {

	private ShiftReportDao dao;
	
	@Autowired
	public ShiftReportServiceImpl(ShiftReportDao dao) {
		this.dao = dao;
	}
	
	@Override
	public void add(ShiftReport shiftReport) {
		dao.add(shiftReport);

	}

	@Override
	public void update(ShiftReport shiftReport) {
		dao.update(shiftReport);

	}

	@Override
	public void delete(int id) {
		dao.delete(id);
	}

	@Override
	public List<ShiftReport> getShiftReport(ShiftReportSearchCriteria searchCriteria) {
		return dao.getShiftReport(searchCriteria);
	}

	@Override
	public ShiftReport getShiftReport(int id) {
		return dao.getShiftReport(id);
	}

}
