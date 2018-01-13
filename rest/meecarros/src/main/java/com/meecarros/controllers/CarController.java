package com.meecarros.controllers;

import java.util.Collection;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.meecarros.models.Car;
import com.meecarros.services.CarService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		Car car = this.service.getCar(carroId);
		if (Objects.isNull(car)) {
			log.info("Não foi encontrado carro com id: " + carroId);
		}
		return car;
	}

}
