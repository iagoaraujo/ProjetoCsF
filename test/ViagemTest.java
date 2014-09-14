import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.callAction;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.session;
import static play.test.Helpers.start;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.InscricaoAberta;
import models.InscricaoException;
import models.InscricaoLimitada;
import models.Usuario;
import models.Viagem;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import play.mvc.Result;
import play.db.jpa.JPA;
import play.test.FakeRequest;
import play.test.Helpers;

public class ViagemTest {

	private CenarioTestes cenarioTestes;
	private GenericDAO dao;

	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase()));
		cenarioTestes = new CenarioTestes();
		dao = new GenericDAOImpl();
	}

	@Test
	public void testPesistenciaDadosValidos() {
		Helpers.running(Helpers.fakeApplication(Helpers.inMemoryDatabase()), new Runnable() {
			public void run() {
				JPA.withTransaction(new play.libs.F.Callback0() {
					@Override
					public void invoke() throws Throwable {
						Viagem viagem = cenarioTestes.getViagemAustralia();
						viagem.setResponsavel(cenarioTestes.getUsuarioCarlos());
						dao.persist(viagem);
						dao.flush();
						Assert.assertTrue(dao.findAllByClassName("viagem")
								.contains(viagem));
						Assert.assertEquals(viagem, dao.findByEntityId(Viagem.class, 
								viagem.getId()));
					}
				});
			}
		});
	}
	
	public void testPesistenciaViagemSemResponsavel() {
		Helpers.running(Helpers.fakeApplication(Helpers.inMemoryDatabase()), new Runnable() {
			public void run() {
				JPA.withTransaction(new play.libs.F.Callback0() {
					@Override
					public void invoke() throws Throwable {
						Viagem viagem = cenarioTestes.getViagemAustralia();
						viagem.setInscricaoStrategy(new InscricaoAberta());
						dao.persist(viagem);
						dao.flush();
					}
				});
			}
		});
	}
	
	@Test
	public void testPesistenciaViagemAbertaComController() {
		Helpers.running(Helpers.fakeApplication(Helpers.inMemoryDatabase()), new Runnable() {
			public void run() {
				JPA.withTransaction(new play.libs.F.Callback0() {
					@Override
					public void invoke() throws Throwable {
						Usuario usuario = cenarioTestes.getUsuarioJoana();
						Map<String, String> form = new HashMap<String, String>();
    	            	form.put("nome", usuario.getNome());
    	            	form.put("email", usuario.getEmail());
    	            	form.put("senha", usuario.getSenha());
    	            	Result result = callAction(controllers.routes.ref
    	            			.RegistroDeUsuario.registrar(), 
    	                		fakeRequest().withFormUrlEncodedBody(form));
    	            	assertThat(!session(result).isEmpty());
    	            	
						Viagem viagem = cenarioTestes.getViagemAustralia();
						viagem.setInscricaoStrategy(new InscricaoAberta());
						Map<String, String> formViagem = new HashMap<String, String>();
    	            	formViagem.put("descricao", viagem.getDescricao());
    	            	formViagem.put("local", viagem.getLocal());
    	            	formViagem.put("dataInicio", viagem.getDataInicio());
    	            	formViagem.put("dataFim", viagem.getDataFim());
    	            	formViagem.put("continente", viagem.getContinente().getContinente());
    	            	formViagem.put("estrategia", "aberta");
    	            	formViagem.put("senha", "");
    	            	FakeRequest fakeRequest = fakeRequest();
    	            	fakeRequest.withFormUrlEncodedBody(formViagem);
    	            	fakeRequest.withSession("user", "1");
    	            	callAction(controllers.routes.ref.Application.cadastrar(), 
    	                		fakeRequest);
    	            	
    	            	List<Viagem> viagens = dao.findAllByClassName("viagem");
    	            	assertThat(viagens.contains(viagem));
					}
				});
			}
		});
	}
	
	@Test
	public void testPesistenciaViagemLimitadaComController() {
		Helpers.running(Helpers.fakeApplication(Helpers.inMemoryDatabase()), new Runnable() {
			public void run() {
				JPA.withTransaction(new play.libs.F.Callback0() {
					@Override
					public void invoke() throws Throwable {
						Usuario usuario = cenarioTestes.getUsuarioJoana();
						Map<String, String> form = new HashMap<String, String>();
    	            	form.put("nome", usuario.getNome());
    	            	form.put("email", usuario.getEmail());
    	            	form.put("senha", usuario.getSenha());
    	            	Result result = callAction(controllers.routes.ref
    	            			.RegistroDeUsuario.registrar(), 
    	                		fakeRequest().withFormUrlEncodedBody(form));
    	            	assertThat(!session(result).isEmpty());
    	            	
						Viagem viagem = cenarioTestes.getViagemAustralia();
						viagem.setInscricaoStrategy(new InscricaoLimitada());
						Map<String, String> formViagem = new HashMap<String, String>();
    	            	formViagem.put("descricao", viagem.getDescricao());
    	            	formViagem.put("local", viagem.getLocal());
    	            	formViagem.put("dataInicio", viagem.getDataInicio());
    	            	formViagem.put("dataFim", viagem.getDataFim());
    	            	formViagem.put("continente", viagem.getContinente().getContinente());
    	            	formViagem.put("estrategia", "limitada");
    	            	formViagem.put("senha", "password");
    	            	FakeRequest fakeRequest = fakeRequest();
    	            	fakeRequest.withFormUrlEncodedBody(formViagem);
    	            	fakeRequest.withSession("user", "1");
    	            	callAction(controllers.routes.ref.Application.cadastrar(), 
    	                		fakeRequest);
    	            	
    	            	List<Viagem> viagens = dao.findAllByClassName("viagem");
    	            	assertThat(viagens.contains(viagem));
					}
				});
			}
		});
	}

	@Test(expected=RuntimeException.class)
	public void testPesistenciaViagemLimitadaComControllerSemSenha() {
		Helpers.running(Helpers.fakeApplication(Helpers.inMemoryDatabase()), new Runnable() {
			public void run() {
				JPA.withTransaction(new play.libs.F.Callback0() {
					@Override
					public void invoke() throws Throwable {
						Usuario usuario = cenarioTestes.getUsuarioJoana();
						Map<String, String> form = new HashMap<String, String>();
    	            	form.put("nome", usuario.getNome());
    	            	form.put("email", usuario.getEmail());
    	            	form.put("senha", usuario.getSenha());
    	            	Result result = callAction(controllers.routes.ref.
    	            			RegistroDeUsuario.registrar(), 
    	                		fakeRequest().withFormUrlEncodedBody(form));
    	            	assertThat(!session(result).isEmpty());
    	            	
						Viagem viagem = cenarioTestes.getViagemAustralia();
						viagem.setInscricaoStrategy(new InscricaoLimitada());
						Map<String, String> formViagem = new HashMap<String, String>();
    	            	formViagem.put("descricao", viagem.getDescricao());
    	            	formViagem.put("local", viagem.getLocal());
    	            	formViagem.put("dataInicio", viagem.getDataInicio());
    	            	formViagem.put("dataFim", viagem.getDataFim());
    	            	formViagem.put("continente", viagem.getContinente().getContinente());
    	            	formViagem.put("estrategia", "limitada");
    	            	formViagem.put("senha", "");
    	            	FakeRequest fakeRequest = fakeRequest();
    	            	fakeRequest.withFormUrlEncodedBody(formViagem);
    	            	fakeRequest.withSession("user", "1");
    	            	callAction(controllers.routes.ref.Application.cadastrar(), 
    	                		fakeRequest);
					}
				});
			}
		});
	}
	
	@Test
	public void testAddParticipante() {
		Viagem viagem = cenarioTestes.getViagemEspanha();
		Usuario carlos = cenarioTestes.getUsuarioCarlos();
		Usuario joana = cenarioTestes.getUsuarioJoana();
		viagem.addParticipante(carlos);
		Assert.assertFalse(viagem.getParticipantes().isEmpty());
		Assert.assertTrue(viagem.getParticipantes().contains(carlos));
		viagem.addParticipante(joana);
		Assert.assertEquals(2, viagem.getParticipantes().size());
		Assert.assertTrue(viagem.getParticipantes().contains(joana));
		viagem.addParticipante(carlos);
		Assert.assertEquals("Nao pode haver participantes repetidos",
				2, viagem.getParticipantes().size());
	}
	
	@Test
	public void testInscricaoAberta() {
		Usuario joao = cenarioTestes.getUsuarioJoao();
		Usuario carlos = cenarioTestes.getUsuarioCarlos();
		Viagem viagem = cenarioTestes.getViagemCanada();
		viagem.setInscricaoStrategy(new InscricaoAberta());
		viagem.inscreverParticipante(joao, "");
		Assert.assertTrue(viagem.getParticipantes().contains(joao));
		viagem.inscreverParticipante(carlos, "senhaAleatoria");
		Assert.assertTrue(viagem.getParticipantes().contains(carlos));
	}
	
	@Test
	public void testInscricaoLimitadaSenhaCorreta() {
		Usuario joana = cenarioTestes.getUsuarioJoana();
		Viagem viagem = cenarioTestes.getViagemRussia();
		viagem.setSenha("password");
		viagem.setInscricaoStrategy(new InscricaoLimitada());
		viagem.inscreverParticipante(joana, viagem.getSenha());
		Assert.assertTrue(viagem.getParticipantes().contains(joana));
	}
	
	@Test(expected=InscricaoException.class)
	public void testInscricaoLimitadaSenhaIncorreta() {
		Usuario joana = cenarioTestes.getUsuarioJoana();
		Viagem viagem = cenarioTestes.getViagemRussia();
		viagem.setSenha("password");
		viagem.setInscricaoStrategy(new InscricaoLimitada());
		viagem.inscreverParticipante(joana, "senhaInvalida");
	}
}
