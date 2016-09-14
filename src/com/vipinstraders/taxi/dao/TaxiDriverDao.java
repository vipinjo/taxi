package com.vipinstraders.taxi.dao;

import java.util.List;

import com.vipinstraders.taxi.domain.Driver;

public interface TaxiDriverDao {

	public void addDriver(Driver driver);

	public void editDriver(Driver driver);
	
	public void deleteDriver(int id);

	public List<Driver> getAllDrivers();

}
