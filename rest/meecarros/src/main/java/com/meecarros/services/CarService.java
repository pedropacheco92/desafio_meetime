package com.meecarros.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.meecarros.models.Car;

@Service
public class CarService {

	private static Map<Long, Map<Long, List<Car>>> carros = new HashMap<>();

	static {
		carros.put(1L, new HashMap<>());
		carros.put(2L, new HashMap<>());

		// carros.get(1L).put(1L, new Car("carro1"));
		// carros.get(1L).put(1L, new Car("carro1"));
	}

	public List<Car> getAllCars() {
		return Arrays.asList(new Car("carro1"), new Car("carro2"));
	}

}
