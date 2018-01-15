package com.meecarros.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.meecarros.models.Car;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CarService {

	private static Map<Long, Car> carros = new HashMap<>();

	@Setter
	private List<Long> currentProspectId = new ArrayList<>();

	public Collection<Car> getAllCars() {
		return carros.values();
	}

	public Car getCar(Long id) {
		return carros.get(id);
	}

	public boolean deleteCar(Long carroId) {
		return Objects.nonNull(carros.remove(carroId));
	}

	public Car editCar(Car car, Long carroId) {
		carros.replace(carroId, car);
		return car;
	}

	public Car saveCar(Car car, Long carroId) {
		if (carroId != 0L) {
			log.info("Erro ao salvar carro, não deveria ter id:" + carroId);
			return null;
		}

		Long id = carros.keySet().stream().max(Long::compareTo).orElse(0L) + 1; // maior id
		car.setId(id);
		carros.put(id, car);
		return car;
	}

}
