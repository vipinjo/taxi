package com.vipinstraders.taxi.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vipinstraders.taxi.dao.TaxiDriverDao;
import com.vipinstraders.taxi.domain.Driver;

@Component
public class DriverServiceImpl implements DriverService {
	
	private TaxiDriverDao dao;
	
	@Autowired
	public DriverServiceImpl(TaxiDriverDao dao) {
		this.dao = dao;
	}

	@Override
	public void addDriver(Driver driver) {
		dao.addDriver(driver);
	}

	@Override
	public void editDriver(Driver driver) {
		dao.editDriver(driver);
	}

	@Override
	public void deleteDriver(int id) {
		dao.deleteDriver(id);
	}

	@Override
	public List<Driver> getAllDrivers() {
		return dao.getAllDrivers();
	}

	
}
