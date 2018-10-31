package com.apap.tutorial7.controller;

import com.apap.tutorial7.model.*;
import com.apap.tutorial7.service.*;
import com.sun.rowset.internal.Row;
import java.util.List;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
public class CarController {
	@Autowired
	private CarService carService;
	
	@Autowired
	private DealerService dealerService;
	
	@PutMapping(value = "/{id}")
	private String updateCarSubmit(
			@PathVariable (value = "id") long id,
			@RequestParam("brand") String brand,
			@RequestParam("type") String type,
			@RequestParam("price") long price,
			@RequestParam("amount") Integer amount,
			@RequestParam("dealerId") long dealerId) {
		
		CarModel car = carService.getCarDetailById(id).get();
		if(car.equals(null)) {
			return "Could not find your car";
		}
		
		car.setBrand(brand);
		car.setType(type);
		car.setPrice(price);
		car.setAmount(amount);

		carService.addCar(car);
		
		return "car update success";
	}
	
	@PostMapping(value = "/add")
	private CarModel addCarSubmit(@RequestBody CarModel car) {
		return carService.addCar(car);
	}
	
	@GetMapping(value = "/{carId}")
	private CarModel viewCar(@PathVariable ("carId") long carId) {
		return carService.getCar(carId);
	}
	
	@GetMapping()
	private List<CarModel> viewAllCar(Model model){
		return carService.getAllCar();
	}
	
	@DeleteMapping(value = "/delete")
    private String deleteCar(@RequestParam("carId") long carId, Model Model) {
    	CarModel car = (CarModel) carService.getCarDetailById(carId).get();
    	carService.deleteCar(car);
    	return "Car has been deleted";
    }
	
}
//	@Autowired
//	private CarService carService;
//	
//	@Autowired
//	private DealerService dealerService;
//	
//	@RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.GET)
//	private String add(@PathVariable(value = "dealerId") Long dealerId, Model model) {
//		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
//		ArrayList<CarModel> list = new ArrayList<CarModel>();
//		list.add(new CarModel());
//		dealer.setListCar(list);
//		model.addAttribute("title", "Add Car");
//		model.addAttribute("dealer", dealer);
//		return "addCar";
//	}
//	
//	@RequestMapping(value = "/car/add", method = RequestMethod.POST)
//	private String addCarSubmit(@ModelAttribute CarModel car, Model model) {
//		carService.addCar(car);
//		model.addAttribute("title", "Add Car");
//		return "add";
//	}
//	
//	
//	@RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.POST, params= {"save"})
//	private String addCarSubmit (@ModelAttribute DealerModel dealer) {
//		DealerModel dealerSkrg = dealerService.getDealerDetailById(dealer.getId()).get();
//		for(CarModel car : dealer.getListCar()) {
//			car.setDealer(dealerSkrg);
//			carService.addCar(car);
//		}
//		
//		return "add";
//	}
//	
//	@RequestMapping(value = "/car/add/{dealerId}", params= {"addRow"}, method = RequestMethod.POST)
//	private String addRow (@ModelAttribute DealerModel dealer, Model model) {
//		dealer.getListCar().add(new CarModel());
//		model.addAttribute("dealer", dealer);
//		return "addCar";
//	}
//	
//	@RequestMapping(value="/car/add/{dealerId}", method = RequestMethod.POST, params={"removeRow"})
//	private String removeRow (@ModelAttribute DealerModel dealer, final BindingResult bindingResult, final HttpServletRequest req, Model model) {
//		final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
//		dealer.getListCar().remove(rowId.intValue());
//		model.addAttribute("dealer", dealer);
//		return "addCar";
//	}
//	
//	@RequestMapping(value="/car/delete/{id}", method=RequestMethod.GET)
//	private String deleteCar(@PathVariable(value = "id") Long id, Model model) {
//			carService.deleteById(id);
//			model.addAttribute("title", "Delete Car");
//			return "delete-car";
//	}
//	
//	@RequestMapping(value="/car/delete", method = RequestMethod.POST)
//	private String delete(@ModelAttribute DealerModel dealer, Model model) {
//		for (CarModel car : dealer.getListCar()) {
//			carService.deleteCar(car);
//		}
//		model.addAttribute("title", "Delete Car");
//		return "delete-car";	
//	}
//	
//	@RequestMapping(value = "/car/update/{id}", method = RequestMethod.GET)
//	private String updateCar(@PathVariable(value = "id") long id, Model model) {
//		CarModel car = carService.getCar(id);
//		model.addAttribute("car",car);
//		model.addAttribute("title", "Update Car");
//		return "update-car";
//	}
//	
//	@RequestMapping(value = "/car/update/{id}", method = RequestMethod.POST)
//	private String updateCarSubmit(@PathVariable(value = "id") long id, @ModelAttribute CarModel car, Model model) {
//		carService.updateCar(id, car);
//		model.addAttribute("title", "Update Car");
//		return "update";
//	}
//	
//}