package com.meecarros.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.meecarros.models.Car;
import com.meecarros.services.CarService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/carros")
public class CarController {

	@Autowired
	private CarService service;

	@RequestMapping(method = RequestMethod.GET)
	public Collection<Car> listAllCars() {
		return this.service.getAllCars();
	}

	@RequestMapping(value = "/{carroId}", method = RequestMethod.GET)
	public Car getCar(@PathVariable Long carroId) {
		return this.service.getCar(carroId);
	}

}
