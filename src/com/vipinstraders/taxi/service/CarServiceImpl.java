package com.vipinstraders.taxi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vipinstraders.taxi.dao.CarDao;
import com.vipinstraders.taxi.domain.Car;

@Component
public class CarServiceImpl implements CarService {
	
	private CarDao dao;
	
	@Autowired
	public CarServiceImpl(CarDao carDao) {
		this.dao = carDao;
	}

	@Override
	public void addCar(Car car) {
        System.out.println("Car make : " + car.getMake());
        System.out.println("Car rego : " + car.getRego());
        dao.createCar(car);
	}

	@Override
	public void editCar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCar() {
		// TODO Auto-generated method stub

	}

}
