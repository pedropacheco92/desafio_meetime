package com.meecarros.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meecarros.models.Car;
import com.meecarros.models.Cor;
import com.meecarros.models.Prospect;

@Service
public class CarService {

	private static Map<Long, Car> carros = new HashMap<>();

	private static Map<Long, List<Long>> prospects = new HashMap<>();

	static {
		carros.put(1L, new Car(1L, "carro1", Cor.PRETO, "2013"));
		carros.put(2L, new Car(2L, "carro2", Cor.BRANCO, "2000"));
		carros.put(3L, new Car(3L, "carro3", Cor.PRETO, "1990"));
	}

	@Autowired
	private ProspectService prostectService;

	@PostConstruct
	private void init() {
		this.prostectService.getAllProspects().stream().map(Prospect::getId).forEach(p -> {
			long car = (long) new Random().nextInt(3) + 1;
			prospects.put(p, new ArrayList<>(Arrays.asList(car)));
		});
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

	public boolean deleteCar(Long carroId, Long prospectId) {
		carros.remove(carroId);
		return prospects.get(prospectId).remove(carroId);
	}

	public void editCar(Car car) {
		carros.replace(car.getId(), car);
	}

	public void saveCar(Car car, Long prospectId) {
		carros.put(car.getId(), car);
		prospects.get(prospectId).add(car.getId());
	}

}
