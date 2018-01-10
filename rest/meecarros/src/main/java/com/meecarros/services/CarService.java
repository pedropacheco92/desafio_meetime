package com.meecarros.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.meecarros.models.Car;

@Service
public class CarService {

	private static Map<Long, List<Car>> carros = new HashMap<>();

	static {
		carros.put(1L, new ArrayList<>());
		carros.put(2L, new ArrayList<>());

		carros.get(1L).addAll(Arrays.asList(new Car(1L, "carro1"), new Car(2L, "carro2")));
	}

	public List<Car> getAllCars() {
		List<Car> allCars = new ArrayList<>();
		carros.values().stream().forEach(allCars::addAll);

		return allCars;
	}

	public List<Car> getCarsByProspectId(Long id) {
		return carros.get(id);
	}

	public Car getCar(Long id) {
		return getAllCars().stream().filter(c -> c.getId().equals(id)).findAny().orElseGet(null);
	}

}
