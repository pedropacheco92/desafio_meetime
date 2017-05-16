package services;

import models.Car;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pedro on 16/05/2017.
 */
public class CarService {

    private Map<Long, Car> carros = new HashMap<>();

    public Car getCarro(Integer carId) {
        return this.carros.get(carId);
    }

    public void addCarro(Car carro) {
        this.carros.put(carro.carId, carro);
    }

    public Boolean updateCarro(Car carro) {
        if (this.carros.containsKey(carro.carId)) {
            this.carros.put(carro.carId, carro);
            return true;
        } else {
            return false;
        }
    }

}
