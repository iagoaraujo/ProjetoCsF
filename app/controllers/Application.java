package controllers;

import java.util.ArrayList;
import java.util.List;

import models.EContinente;
import models.Viagem;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

	private static GenericDAO dao = new GenericDAOImpl();
	
    public static Result index() {
        return ok(views.html.login.render());
    }

    public static Result inicio() {
    	List<Viagem> viagens = new ArrayList<Viagem>();
    	return ok(views.html.continente.render(EContinente.EUROPA.getContinente(),
    			viagens));
    }
    
    public static Result continentesDaEuropa() {
    	List<Viagem> viagens = new ArrayList<Viagem>();
    	return ok(views.html.continente.render(EContinente.EUROPA.getContinente(),
    			viagens));
    }
    
    public static Result continentesDaAfrica() {
    	List<Viagem> viagens = new ArrayList<Viagem>();
    	return ok(views.html.continente.render(EContinente.AFRICA.getContinente(),
    			viagens));
    }    
    
    public static Result continentesDaAsia() {
    	List<Viagem> viagens = new ArrayList<Viagem>();
    	return ok(views.html.continente.render(EContinente.ASIA.getContinente(),
    			viagens));
    }
    
    public static Result continentesDaAmericaDoNorte() {
    	List<Viagem> viagens = new ArrayList<Viagem>();
    	return ok(views.html.continente.render(EContinente.AMERICA_DO_NORTE.getContinente(),
    			viagens));
    }
    
    public static Result continentesDaAmericaLatina() {
    	List<Viagem> viagens = new ArrayList<Viagem>();
    	return ok(views.html.continente.render(EContinente.AMERICA_LATINA.getContinente(),
    			viagens));
    }
    
    public static Result continentesDaOceania() {
    	List<Viagem> viagens = new ArrayList<Viagem>();
    	return ok(views.html.continente.render(EContinente.OCEANIA.getContinente(),
    			viagens));
    }
    
    private static GenericDAO getDao() {
    	return dao;
    }
}
