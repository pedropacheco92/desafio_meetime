package com.meecarros.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.meecarros.models.Car;

@Service
public class CarService {

	private static Map<Long, Car> carros = new HashMap<>();

	private static Map<Long, List<Long>> prospects = new HashMap<>();

	static {
		carros.put(1L, new Car(1L, "carro1"));
		carros.put(2L, new Car(2L, "carro2"));
		carros.put(3L, new Car(3L, "carro3"));

		prospects.put(1L, Arrays.asList(1L, 2L));
		prospects.put(2L, Arrays.asList(3L));
	}

	public Collection<Car> getAllCars() {
		return carros.values();
	}

	public Collection<Car> getCarsByProspectId(Long id) {
		return prospects.get(id).stream().map(carros::get).collect(Collectors.toList());
	}

	public Car getCar(Long id) {
		return carros.get(id);
	}

	public void deleteCar(Long carroId, Long prospectId) {
		carros.remove(carroId);
		prospects.get(prospectId).remove(carroId);
	}

	public void editCar(Car car) {
		carros.replace(car.getId(), car);
	}

	public void saveCar(Car car, Long prospectId) {
		carros.put(car.getId(), car);
		prospects.get(prospectId).add(car.getId());
	}

}
