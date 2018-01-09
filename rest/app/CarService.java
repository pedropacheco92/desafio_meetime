import models.Car;

import java.util.*;

/**
 * Created by Pedro on 16/05/2017.
 */
public class CarService {

    private Map<Long, Car> carros = new HashMap<>();

    public Car getCarroById(Long carId) {
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

    public Boolean deleteCarroById(Long carId) {
        return this.carros.remove(carId) != null;
    }

    public List<Car> getAllCars(){
        return new ArrayList<>(carros.values());
    }

}
