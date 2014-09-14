package controllers;

import static play.data.Form.form;

import java.util.List;




import models.Usuario;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.mvc.Controller;
import play.mvc.Result;
import play.data.Form;
import play.db.jpa.Transactional;
import views.html.login;

public class Login extends Controller {
	
	private static GenericDAO dao = new GenericDAOImpl();
	static Form<Usuario> loginForm = form(Usuario.class).bindFromRequest();

	@Transactional
    public static Result show() {
			if (getDao().findAllByClassName("viagem").isEmpty()) {
				GeradordeExemplos g = new GeradordeExemplos();
				g.gera();
			}
		if (session().get("user") != null) {
			return redirect(routes.Application.index());
		}
        return ok(views.html.login.render(getLoginForm()));
    }
	
	@Transactional
	public static Result logout() {
		session().clear();
		return show();
	}
	
	
    
	@Transactional
	public static Result authenticate() {
		
		Usuario pessoa = getLoginForm().bindFromRequest().get();
		Usuario user = UsuarioNoSistemaESenhaOk(pessoa);
		if (user==null) {	
			flash("fail","Usuario ou senha invalidos!");
			return badRequest(login.render(getLoginForm()));
		}
		session().clear();
		session("user", user.getId().toString());
		
		return redirect(routes.Application.index());
	}
	
	
	
	@Transactional
	private static Usuario UsuarioNoSistemaESenhaOk(Usuario pessoa) {
		List<Usuario> pessoas = getDao().findAllByClassName("usuario");
		for (Usuario usuario: pessoas) {
			if (usuario.equals(pessoa) && usuario.getSenha().equals(pessoa.getSenha())) {
				return usuario;
			}
		}
		return null;
	}
	
	private static boolean validate(String email, String senha) {
		List<Usuario> u = dao.findByAttributeName("Usuario", "email", email);
		if (u == null || u.isEmpty()) {
			return false;
		}
		if (!u.get(0).getSenha().equals(senha)) {
			return false;
		}
		return true;
	}
	
	private static GenericDAO getDao() {
		return dao;
	}
	
	private static Form<Usuario> getLoginForm() {
		return loginForm;
	}
}