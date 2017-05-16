package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Car;
import play.libs.Json;
import play.mvc.*;
import services.CarService;
import views.html.index;

import javax.inject.*;

/**
 * Created by pedro on 11/05/17.
 */
@Singleton
public class CarController extends Controller {

    private CarService service = new CarService();

    public Result getCarros(Long token) {
        Car car = new Car();
        car.carId = token;
        car.userId = 123L;
        car.ano = "2015";
        car.cor = "preta";
        car.modelo = "uno";


        JsonNode result = Json.toJson(car);
        return ok(result);
    }

    public Result create() {
        JsonNode json = request().body().asJson();
        if (json == null){
            return badRequest(Util.createResponse(
                    "Expecting Json data", false));
        }

        Car carro = Json.fromJson(json, Car.class);
        service.addCarro(carro);
        JsonNode jsonObject = Json.toJson(carro);
        return created(Util.createResponse(jsonObject, true));
    }


}
