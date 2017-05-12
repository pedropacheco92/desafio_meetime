package controllers;

import play.mvc.*;
import views.html.index;

import javax.inject.*;

/**
 * Created by pedro on 11/05/17.
 */
@Singleton
public class CarController extends Controller {

    public Result getCarros(Long token) {
        return ok("carro");
    }

}
