package controllers;


import static play.data.Form.form;

import javax.persistence.PersistenceException;

import models.Usuario;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.registro;

public class RegistroDeUsuario extends Controller {
	
	private static GenericDAO dao = new GenericDAOImpl();
	static Form<Usuario> registroForm = form(Usuario.class).bindFromRequest();

	@Transactional
    public static Result show() {
        return ok(registro.render(registroForm));
    }
    
	@Transactional
	public static Result registrar() {
		Form<Usuario> filledForm = registroForm.bindFromRequest(); 
		Usuario user = filledForm.get();
		try {
			getDao().merge(user);
			getDao().flush();		
			flash("sucesso","Usuario criado com sucesso");
			return redirect(routes.Login.show());
		} catch (PersistenceException ex) {
			flash("fail","Dados já em uso. Tente um nome ou email diferentes");
			return redirect(routes.RegistroDeUsuario.show());
		}
    }
	
	private static GenericDAO getDao() {
		return dao;
	}

}
