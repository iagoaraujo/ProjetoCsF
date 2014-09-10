package controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import models.EContinente;
import models.InscricaoAberta;
import models.InscricaoLimitada;
import models.Viagem;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.continente;
import views.html.cadastroViagem;
import views.html.continente;
import views.html.inicio;
import views.html.viagemCriadaComSucesso;


public class Application extends Controller {
	static Form<Viagem> viagemForm = Form.form(Viagem.class);

	private static GenericDAO dao = new GenericDAOImpl();
	
	@Transactional
    public static Result index() {
        return ok(views.html.login.render());
    }
    
	@Transactional
    public static Result start() {
    	Viagem viagem = new Viagem();
    	viagem.setContinente(EContinente.EUROPA);
    	viagem.setPais("Espanha");
    	viagem.setDataFim(Calendar.getInstance().getTime());
    	viagem.setDataInicio(Calendar.getInstance().getTime());
    	viagem.setDescricao("Vamos viajar muitos");
    	viagem.setInscricaoStrategy(new InscricaoAberta());
    	Viagem viagem2 = new Viagem();
    	viagem2.setContinente(EContinente.EUROPA);
    	viagem2.setPais("Egito");
    	viagem2.setDataFim(Calendar.getInstance().getTime());
    	viagem2.setDataInicio(Calendar.getInstance().getTime());
    	viagem2.setDescricao("Vamos viajar muitos");
    	viagem2.setInscricaoStrategy(new InscricaoLimitada());
    	getDao().persist(viagem);
    	getDao().persist(viagem2);
    	getDao().flush();
        return ok(views.html.inicio.render());
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
    public static Result visualizarViagem(Long id) {
    	Viagem viagem = getDao().findByEntityId(Viagem.class, id);
    	return ok(views.html.verviagem.render(viagem));
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
