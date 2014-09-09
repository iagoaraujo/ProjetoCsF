package controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import models.EContinente;
import models.InscricaoAberta;
import models.Viagem;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.continente;
import views.html.cadastroViagem;

public class Application extends Controller {
	static Form<Viagem> viagemForm = Form.form(Viagem.class);

	private static GenericDAO dao = new GenericDAOImpl();
	
	@Transactional
    public static Result index() {
    	Viagem europa = new Viagem();
    	europa.setContinente(EContinente.EUROPA);
    	europa.setDataInicio(Calendar.getInstance().getTime());
    	europa.setDataFim(Calendar.getInstance().getTime());
    	europa.setInscricaoStrategy(new InscricaoAberta());
    	europa.setDescricao("Descricao");
    	europa.setPais("Espanha");
    	Viagem africa = new Viagem();
    	africa.setContinente(EContinente.AFRICA);
    	africa.setDataInicio(Calendar.getInstance().getTime());
    	africa.setDataFim(Calendar.getInstance().getTime());
    	africa.setInscricaoStrategy(new InscricaoAberta());
    	africa.setDescricao("Descricao");
    	africa.setPais("Egito");
    	getDao().persist(europa);
    	getDao().persist(africa);
    	getDao().flush();
        return ok(views.html.login.render());
    }

    public static Result inicio() {
    	List<Viagem> viagens = new ArrayList<Viagem>();
    	return ok(views.html.continente.render(EContinente.EUROPA.getContinente(),
    			viagens));
    }
    
	@Transactional
    public static Result continentesDaEuropa() {
    	List<Viagem> viagens = getDao().findAllByClassName("viagem");
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
    
    public static Result cadastro(){
   	 return ok(cadastroViagem.render(viagemForm));
   }
    
    @Transactional
    public static Result cadastrar(){
    	Form<Viagem> filledForm = viagemForm.bindFromRequest();
    	cadastraViagem(filledForm.get());
    	return redirect(routes.Application.index());
    }
    
    private static void cadastraViagem(Viagem viagem){
    	getDao().merge(viagem);
    	getDao().flush();
    }
    
    private static GenericDAO getDao() {
    	return dao;
    }
}
