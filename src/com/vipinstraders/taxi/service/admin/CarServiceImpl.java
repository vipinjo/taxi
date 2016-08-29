package com.vipinstraders.taxi.service.admin;

import java.util.List;

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
        dao.createCar(car);
	}

	@Override
	public void editCar(Car car) {
		dao.editCar(car);
	}

	@Override
	public void deleteCar(String id) {
		dao.deleteCar(id);

	}
	
	@Override
	public List<Car> getAllCars() {
		return dao.getAllCars();
	}

}
