package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Car;
import play.libs.Json;
import play.mvc.*;
import services.CarService;

import javax.inject.*;
import java.util.List;

/**
 * Created by pedro on 11/05/17.
 */
@Singleton
public class CarController extends Controller {

    private CarService service = new CarService();

    private ObjectNode createObjectNode(Object response, boolean ok) {
        ObjectNode result = Json.newObject();
        result.put("isSuccessfull", ok);
        if (response instanceof String) {
            result.put("body", (String) response);
        }
        else {
            result.put("body", (JsonNode) response);
        }

        return result;
    }

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
            return badRequest(createObjectNode("Json faltando", false));
        }

        Car carro = Json.fromJson(json, Car.class);
        service.addCarro(carro);
        JsonNode jsonObject = Json.toJson(carro);
        return created(createObjectNode(jsonObject, true));
    }

    public Result update() {
        JsonNode json = request().body().asJson();
        if (json == null){
            return badRequest(createObjectNode("Json faltando", false));
        }

        Boolean update = service.updateCarro(Json.fromJson(json, Car.class));

        String msg = update ? "Carro atualizado!" : "Carro nao atualizado!";

        ObjectNode node = createObjectNode(msg, update);

        if (!update) {
            return notFound(node);
        }

        return ok(node);
    }

    public Result retrieve(Long id) {
        JsonNode json = request().body().asJson();
        Car carro = service.getCarroById(id);
        if (carro == null){
            return badRequest(createObjectNode("Carro com id:" + id + " nao encontrado", false));
        }

        JsonNode jsonObjects = Json.toJson(carro);
        return ok(createObjectNode(jsonObjects, true));
    }

    public Result delete(Long id) {
        if (!service.deleteCarroById(id)) {
            return badRequest(createObjectNode("Carro com id:" + id + " nao encontrado", false));
        }
        return ok(createObjectNode("Carro com id:" + id + " foi deletado", true));
    }

    public Result listCars() {
        List<Car> result = service.getAllCars();
        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
        return ok(createObjectNode(jsonData, true));
    }


}
