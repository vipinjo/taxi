package com.vipinstraders.taxi.service;

import java.util.List;

import com.vipinstraders.taxi.domain.Car;

public interface CarService {
	
	public void addCar(Car car);
	
	public void editCar(Car car);
	
	public void deleteCar(String id);
	
	public List<Car> getAllCars();

}
