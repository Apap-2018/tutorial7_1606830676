package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.model.CarModel;

public interface CarService {
	CarModel addCar(CarModel car);
	void updateCar(long id,CarModel car);
	void deleteCar(CarModel car);
	CarModel getCar(Long id);
	List<CarModel> getListCardOrderByPriveAsc(Long dealerId);
	List<CarModel> getAllCar();
	Optional<CarModel> getCarDetailById(Long id);
	
	public void deleteById(Long id);
}