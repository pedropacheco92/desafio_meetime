package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Car;
import play.libs.Json;
import play.mvc.*;
import views.html.index;

import javax.inject.*;

/**
 * Created by pedro on 11/05/17.
 */
@Singleton
public class CarController extends Controller {

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

}
