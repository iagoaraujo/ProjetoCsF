package controllers;

import java.util.ArrayList;
import java.util.List;

import models.EContinente;
import models.Usuario;
import models.Viagem;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.cadastroViagem;


public class Application extends Controller {
	private static Form<Usuario> usuarioForm = Form.form(Usuario.class);
	static Form<Viagem> viagemForm = Form.form(Viagem.class);

	private static GenericDAO dao = new GenericDAOImpl();
	
	@Transactional
    public static Result index() {
		if (session().get("user") == null) {
    		return redirect(routes.Login.show());
    	}
    	return ok(views.html.inicio.render());
    }
    
	@Transactional
    public static Result cadastrarUsuario() {
    	Form<Usuario> filledForm = usuarioForm.bindFromRequest(); 
    	getDao().merge(filledForm.get());
    	getDao().flush();
    	System.out.println(filledForm.get().getNome());
    	return ok(views.html.cadastroUsuarioSucesso.render());
	}
	
    public static Result start() {
        return ok(views.html.inicio.render());
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
