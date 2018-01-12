package com.meecarros.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meecarros.models.Car;
import com.meecarros.models.Cor;
import com.meecarros.models.Person;

@Service
public class CarService {

	private static Map<Long, Car> carros = new HashMap<>();

	private static Map<Long, Person> persons = new HashMap<>();

	static {
		carros.put(1L, new Car(1L, "carro1", Cor.PRETO, "2013", null));
		carros.put(2L, new Car(2L, "carro2", Cor.BRANCO, "2000", null));
		carros.put(3L, new Car(3L, "carro3", Cor.PRETO, "1990", null));
	}

	@Autowired
	private PersonService prostectService;

	@PostConstruct
	private void init() {
		this.prostectService.getAllProspects().stream().forEach(p -> persons.put(p.getId(), p));

		// todos os carros inicias somente com a pessoa 1
		carros.values().stream().forEach(car -> car.setPerson(persons.get(1L)));
	}

	public Collection<Car> getAllCars() {
		return carros.values();
	}

	public Collection<Car> getCarsByPersonId(Long id) {
		if (persons.containsKey(id)) {
			return carros.values()
					.stream()
					.filter(car -> id.equals(car.getPerson().getId()))
					.collect(Collectors.toList());
		} else {
			return new ArrayList<>();
		}
	}

	public Car getCar(Long id) {
		return carros.get(id);
	}

	public boolean deleteCar(Long carroId, Long prospectId) {
		if (!persons.containsKey(prospectId)) {
			return false;
		}

		return Objects.nonNull(carros.remove(carroId));
	}

	public Car editCar(Car car) {
		carros.replace(car.getId(), car);
		return car;
	}

	public Car saveCar(Car car) {
		Long id = carros.keySet().stream().max(Long::compareTo).get() + 1;
		car.setId(id);
		carros.put(id, car);
		return car;
	}

}
