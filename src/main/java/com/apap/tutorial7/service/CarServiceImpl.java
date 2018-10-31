package com.apap.tutorial7.service;
import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.model.CarModel;
import com.apap.tutorial7.model.DealerModel;
import com.apap.tutorial7.repository.CarDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarServiceImpl implements CarService {
	@Autowired
	 private CarDb carDb;
	 
	 @Override
	 public CarModel addCar(CarModel car) {
			return carDb.save(car);
			}

	@Override
	public void deleteById(Long id) {
		carDb.deleteById(id);
	}
	
	@Override
	public void updateCar(long id, CarModel newCar) {
		CarModel temp = carDb.getOne(id);
		temp.setBrand(newCar.getBrand());
		temp.setType(newCar.getType());
		temp.setPrice(newCar.getPrice());
		temp.setAmount(newCar.getAmount());
		carDb.save(temp);
	}
	
	public CarModel getCar(Long id) {
		return carDb.findById(id).get();
	}

	@Override
	public void deleteCar(CarModel car) {
		carDb.delete(car);
	}

	@Override
	public List<CarModel> getListCardOrderByPriveAsc(Long dealerId) {
		return carDb.findByDealerIdOrderByPriceAsc(dealerId);
	}
	
    @Override
    public List<CarModel> getAllCar() {
        return carDb.findAll();
    }
    
    @Override
    public Optional<CarModel> getCarDetailById(Long id) {
        return carDb.findById(id);
    }

}