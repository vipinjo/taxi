package com.vipinstraders.taxi.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vipinstraders.taxi.dao.ShiftReportDao;
import com.vipinstraders.taxi.domain.ShiftReport;

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
	public void edit(ShiftReport shiftReport) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

}
