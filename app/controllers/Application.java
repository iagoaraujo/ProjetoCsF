package controllers;

import static play.data.Form.form;

import java.util.List;

import javax.swing.text.html.FormView;

import models.EContinente;
import models.InscricaoStrategy;
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
import views.html.minhaviagem;
import views.html.registro;




public class Application extends Controller {
	private static Form<Usuario> usuarioForm = Form.form(Usuario.class);
	static Form<Viagem> viagemForm = Form.form(Viagem.class);
	static Form<Usuario> registroForm = form(Usuario.class).bindFromRequest();
	

	private static GenericDAO dao = new GenericDAOImpl();

	@Transactional
	public static Result index() {
		if (session().get("user") == null) {
			return redirect(routes.Login.show());
		}
		List<Viagem> viagens = getUsuarioLogado().getViagensCriadas();
		return ok(views.html.inicio.render(viagens));
	}

	@Transactional
	public static Result cadastrarUsuario() {
		Form<Usuario> filledForm = registroForm.bindFromRequest(); 
		Usuario user = registroForm.bindFromRequest().get();
		
		/*if ((getDao().findAllByClassName("Usuario")).contains(user)){
			flash("fail","Usuario já existe no sistema");
			return ok(registro.render(registroForm));
		}
		else{*/
			getDao().merge(filledForm.get());
			getDao().flush();		
			flash("sucesso","Usuario criado com sucesso");
			return redirect(routes.Login.show());
			
		}
		
	//}

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
	public static Result visualizarViagemParaEditar(Long id) {		
		Viagem viagem = getDao().findByEntityId(Viagem.class, id);
		return ok(views.html.minhaviagem.render(viagem));
	}


	@Transactional
	public static Result cadastrar(){
		Form<Viagem> form = viagemForm.bindFromRequest("descricao", "local",
				"dataInicio", "dataFim");
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
/*	
	@Transactional
	public static Result alterar(Long id) {
		
		Viagem viagem = getDao().findByEntityId(Viagem.class, id);
		
		Form<Viagem> form = viagemForm.bindFromRequest("descricao", "local",
				"dataInicio", "dataFim");
		
		DynamicForm requestData = Form.form().bindFromRequest();
		String tipoDeInscricao = requestData.get("estrategia");
		String continente = requestData.get("continente");
		viagem.setContinente(EContinente.getEnum(continente));
		viagem.setInscricaoStrategy(Utils.getInstanciaInscricaoStrategy(tipoDeInscricao));
		viagem.setResponsavel(getUsuarioLogado());
		if (viagem.getInscricaoStrategy().exigeSenha()) {
			viagem.setSenha(requestData.get("senha"));
		}
		
		
		getDao().merge(viagem);
		//getDao().flush();
		return redirect(routes.Application.index());
	}*/
	
/*	@Transactional
	public static Result alterar(Long id) {
		Viagem viagem = getDao().findByEntityId(Viagem.class, id);
		
		Form<Viagem> form = viagemForm.bindFromRequest("descricao", "local",
				"dataInicio", "dataFim");
		Viagem viagem1 = form.get();
		DynamicForm requestData = Form.form().bindFromRequest();
		String tipoDeInscricao = requestData.get("estrategia");
		String continente = requestData.get("continente");
		viagem1.setContinente(EContinente.getEnum(continente));
		viagem1.setInscricaoStrategy(Utils.getInstanciaInscricaoStrategy(tipoDeInscricao));
		viagem1.setResponsavel(getUsuarioLogado());
		if (viagem.getInscricaoStrategy().exigeSenha()) {
			viagem.setSenha(requestData.get("senha"));
		}
		
		
		viagem = viagem1;
		getDao().merge(viagem);
		getDao().flush();
		
		return redirect(routes.Application.index());
	}*/
	
/*	@Transactional
	public static Result alterar(Long id){
		Form<Viagem> form = viagemForm.bindFromRequest("descricao", "local",
				"dataInicio", "dataFim");
		Viagem viagem = form.get();
		DynamicForm requestData = Form.form().bindFromRequest();
		
		String tipoDeInscricao = requestData.get("estrategia");
		InscricaoStrategy inscricaoStrategy = Utils.getInstanciaInscricaoStrategy(tipoDeInscricao);

		
		
		String continente = requestData.get("continente");
		viagem.setContinente(EContinente.getEnum(continente));
		
		viagem.setInscricaoStrategy(inscricaoStrategy);
		
		if (viagem.getInscricaoStrategy().exigeSenha()) {
			viagem.setSenha(requestData.get("senha"));
		}
		
		getDao().merge(viagem);
		getDao().persist(inscricaoStrategy);
		getDao().flush();
		
		return redirect(routes.Application.index());
	}
*/
	
		@Transactional
	public static Result alterar(Long id){
		Form<Viagem> form = viagemForm.bindFromRequest("descricao", "local",
				"dataInicio", "dataFim");
		Viagem aEditar = getDao().findByEntityId(Viagem.class, id);
		DynamicForm requestData = Form.form().bindFromRequest();
		
		aEditar.setDescricao(form.get().getDescricao());
		aEditar.setLocal(form.get().getLocal());
		aEditar.setContinente(form.get().getContinente());
		aEditar.setDataInicio(form.get().getDataInicio());
		aEditar.setDataFim(form.get().getDataFim());
		
		
		
		String tipoDeInscricao = requestData.get("estrategia");
		InscricaoStrategy inscricaoStrategy = Utils.getInstanciaInscricaoStrategy(tipoDeInscricao);

		
		
		String continente = requestData.get("continente");
		aEditar.setContinente(EContinente.getEnum(continente));
		
		aEditar.setInscricaoStrategy(inscricaoStrategy);
		getDao().persist(inscricaoStrategy);
		getDao().flush();
		
		
		if (aEditar.getInscricaoStrategy().exigeSenha()) {
			aEditar.setSenha(requestData.get("senha"));
		}
		
		getDao().merge(aEditar);
		getDao().flush();
		
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
