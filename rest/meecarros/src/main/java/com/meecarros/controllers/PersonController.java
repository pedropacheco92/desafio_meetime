package com.meecarros.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.meecarros.models.Car;
import com.meecarros.models.Person;
import com.meecarros.services.CarService;
import com.meecarros.services.PersonService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1/persons")
public class PersonController {

	@Autowired
	private PersonService service;

	@Autowired
	private CarService carService;

	@RequestMapping(value = "/{personId}", method = RequestMethod.GET)
	public Person getPerson(@PathVariable Long personId) {
		return this.service.getPessoa(personId);
	}

	@RequestMapping(method = RequestMethod.GET)
	public Collection<Person> getAllProspects() {
		return this.service.getAllProspects();
	}

	@RequestMapping(value = "/{personId}/cars", method = RequestMethod.GET)
	public Collection<Car> getCarsBypersonId(@PathVariable Long personId) {
		return this.carService.getCarsByPersonId(personId);
	}

	@RequestMapping(value = "/{personId}/cars/{carroId}", method = RequestMethod.DELETE)
	public boolean deleteCar(@PathVariable Long carroId, @PathVariable Long personId) {
		return this.carService.deleteCar(carroId, personId);
	}

	@RequestMapping(value = "/{personId}/cars/{carroId}", method = RequestMethod.POST)
	public Car saveCar(@RequestBody Car car, @PathVariable Long carroId, @PathVariable Long personId) {
		return this.carService.saveCar(car);
	}

	@RequestMapping(value = "/{personId}/cars/{carroId}", method = RequestMethod.PUT)
	public Car editCar(@RequestBody Car car, @PathVariable Long carroId, @PathVariable Long personId) {
		return this.carService.editCar(car);
	}

}
