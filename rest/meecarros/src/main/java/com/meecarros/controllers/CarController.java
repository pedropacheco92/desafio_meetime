package com.meecarros.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	public List<Car> listAllCars() {
		return this.service.getAllCars();
	}
}
