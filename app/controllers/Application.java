package controllers;

import java.util.List;

import models.EContinente;
import models.Usuario;
import models.Viagem;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import models.utils.Utils;
import play.data.DynamicForm;
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
			System.out.println("index");
			return redirect(routes.Login.show());
		}
		List<Viagem> viagens = getUsuarioLogado().getViagensCriadas();
		return ok(views.html.inicio.render(viagens));
	}

	@Transactional
	public static Result cadastrarUsuario() {
		Form<Usuario> filledForm = usuarioForm.bindFromRequest(); 
		getDao().merge(filledForm.get());
		getDao().flush();
		System.out.println(filledForm.get().getNome());
		return ok(views.html.cadastroUsuarioSucesso.render());
	}

	@Transactional
	public static Result continentesDaEuropa() {
		List<Viagem> viagens = Utils.filtraViagensPorContinente(EContinente.EUROPA,
				Utils.getListaViagensNaoInscritasDoUsuario(getUsuarioLogado(), 
						getViagensDoSistema()));
		return ok(views.html.continente.render(EContinente.EUROPA.getContinente(),
				viagens));
	}

	@Transactional
	public static Result continentesDaAfrica() {
		List<Viagem> viagens = Utils.filtraViagensPorContinente(EContinente.AFRICA,
				Utils.getListaViagensNaoInscritasDoUsuario(getUsuarioLogado(), 
						getViagensDoSistema()));
		return ok(views.html.continente.render(EContinente.AFRICA.getContinente(),
				viagens));
	}    

	@Transactional
	public static Result continentesDaAsia() {
		List<Viagem> viagens = Utils.filtraViagensPorContinente(EContinente.ASIA,
				Utils.getListaViagensNaoInscritasDoUsuario(getUsuarioLogado(), 
						getViagensDoSistema()));
		return ok(views.html.continente.render(EContinente.ASIA.getContinente(),
				viagens));
	}

	@Transactional
	public static Result continentesDaAmericaDoNorte() {
		List<Viagem> viagens = Utils.filtraViagensPorContinente(EContinente.AMERICA_DO_NORTE,
				Utils.getListaViagensNaoInscritasDoUsuario(getUsuarioLogado(), 
						getViagensDoSistema()));
		return ok(views.html.continente.render(EContinente.AMERICA_DO_NORTE.getContinente(),
				viagens));
	}

	@Transactional
	public static Result continentesDaAmericaLatina() {
		List<Viagem> viagens = Utils.filtraViagensPorContinente(EContinente.AMERICA_LATINA,
				Utils.getListaViagensNaoInscritasDoUsuario(getUsuarioLogado(), 
						getViagensDoSistema()));
		return ok(views.html.continente.render(EContinente.AMERICA_LATINA.getContinente(),
				viagens));
	}

	@Transactional
	public static Result continentesDaOceania() {
		List<Viagem> viagens = Utils.filtraViagensPorContinente(EContinente.OCEANIA,
				Utils.getListaViagensNaoInscritasDoUsuario(getUsuarioLogado(), 
						getViagensDoSistema()));
		return ok(views.html.continente.render(EContinente.OCEANIA.getContinente(),
				viagens));
	}

	public static Result cadastro(){
		return ok(cadastroViagem.render(viagemForm));
	}

	@Transactional
	public static Result participar(Long id) {
		Viagem viagem = getDao().findByEntityId(Viagem.class, id);
		//verificar se precisa de senha e se ela é válida
		if (viagem.getInscricaoStrategy().exigeSenha()) {
			DynamicForm requestData = Form.form().bindFromRequest();
			String senha = requestData.get("senha");
			if (senha.equals(viagem.getSenha())) {
				flash("sucesso","Inscrição realizada. Boa viagem!!");
				viagem.addParticipante(getUsuarioLogado());
			}
			else {
				flash("fail","Senha inválida. Tente novamente.");
				return redirect(routes.Application.visualizarViagemParaParticipar(id));
			}
		}
		else {
			flash("sucesso","Inscrição realizada. Boa viagem!!");
			viagem.addParticipante(getUsuarioLogado());
		}
		getDao().merge(viagem);
		getDao().flush();
		return redirect(routes.Application.viagensInscritasDoUsuario());
	}

	@Transactional
	public static Result visualizarViagem(Long id) {
		Viagem viagem = getDao().findByEntityId(Viagem.class, id);
		return ok(views.html.verviagem.render(viagem, getUsuarioLogado(), true));
	}
	
	@Transactional
	public static Result visualizarViagemParaParticipar(Long id) {
		Viagem viagem = getDao().findByEntityId(Viagem.class, id);
		return ok(views.html.verviagem.render(viagem, getUsuarioLogado(), false));
	}

	@Transactional
	public static Result cadastrar(){
		Form<Viagem> form = viagemForm.bindFromRequest("descricao", "local",
				"dataInicio", "dataFim");
		System.out.println(form.data());
		Viagem viagem = form.get();
		DynamicForm requestData = Form.form().bindFromRequest();
		String tipoDeInscricao = requestData.get("estrategia");
		String continente = requestData.get("continente");
		viagem.setContinente(EContinente.getEnum(continente));
		viagem.setInscricaoStrategy(Utils.getInstanciaInscricaoStrategy(tipoDeInscricao));
		viagem.setResponsavel(getUsuarioLogado());
		if (viagem.getInscricaoStrategy().exigeSenha()) {
			viagem.setSenha(requestData.get("senha"));
		}
		cadastraViagem(viagem);
		flash("sucesso","Viagem cadastrada com sucesso");
		return redirect(routes.Application.index());
	}

	private static void cadastraViagem(Viagem viagem){
		getDao().persist(viagem);
		getDao().flush();
	}

	private static GenericDAO getDao() {
		return dao;
	}

	private static Usuario getUsuarioLogado() {
		Usuario usuario = getDao().findByEntityId(Usuario.class, 
				Long.valueOf(session().get("user")));
		return usuario;
	}
	
	private static List<Viagem> getViagensDoSistema() {
		List<Viagem> viagens = getDao().findAllByClassName("viagem");
		return viagens;
	}
	
	@Transactional
	public static Result viagensInscritasDoUsuario() {
		Usuario usuario = getDao().findByEntityId(Usuario.class, 
				Long.valueOf(session().get("user")));
		return ok(views.html.viagensInscritas.render(usuario.getViagensInscritas()));
	}
}
