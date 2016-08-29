package com.vipinstraders.taxi.dao;

import java.util.List;

import com.vipinstraders.taxi.domain.Car;

public interface CarDao {
	
	public void createCar(Car car);
	
	public void editCar(Car car);
	
	public List<Car> getAllCars();
	
	public void deleteCar(String id);

}
