package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(views.html.login.render());
    }

    public static Result inicio() {
    	return ok(views.html.continente.render());
    }
}
