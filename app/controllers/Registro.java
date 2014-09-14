package controllers;


import static play.data.Form.form;

import java.util.List;

import models.Usuario;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.registro;

public class Registro extends Controller {
	
	private static GenericDAO dao = new GenericDAOImpl();
	static Form<Usuario> registroForm = form(Usuario.class).bindFromRequest();

	@Transactional
    public static Result show() {
        return ok(registro.render(registroForm));
    }
    
	@Transactional
	public static Result registrar() {
		
		Usuario u = registroForm.bindFromRequest().get();    	
		if (registroForm.hasErrors() || !dao.findByAttributeName("Usuario", "nome", u.getNome()).isEmpty()) {
			flash("fail", "Email já está em uso");
			System.out.println("verificauso");
            return badRequest(registro.render(registroForm));
        } else {
        	dao.persist(u);
        	
            return redirect( routes.Login.show()
            );
        }
    }
	
	@Transactional
	private static boolean userRegistered(Usuario pessoa) {
		List<Usuario> pessoas = dao.findAllByClassName("usuario");
		for (Usuario usuario: pessoas) {
			if (usuario.equals(pessoa)) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean validate(String email) {
		return true;
	}

}
