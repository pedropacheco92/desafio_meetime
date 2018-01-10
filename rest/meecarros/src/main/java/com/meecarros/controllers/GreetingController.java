package com.meecarros.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meecarros.models.Car;
import com.meecarros.models.Greeting;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(this.counter.incrementAndGet(),
				String.format(template, name));
	}

	@RequestMapping(path = "/carros", method = RequestMethod.GET)
	public List<Car> listAllCars() {
		return Arrays.asList(new Car("teste"));
	}
}