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
import models.Usuario;
import models.Viagem;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Result;
import play.test.Helpers;


public class UsuarioTest {

	private CenarioTestes cenarioTestes = new CenarioTestes();
	private GenericDAO dao = new GenericDAOImpl();

	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase()));
	}

	@Test
	public void testPersistenciaDadosValidosDoUsuario() {
		Helpers.running(Helpers.fakeApplication(Helpers.inMemoryDatabase()), new Runnable() {
			public void run() {
				JPA.withTransaction(new play.libs.F.Callback0() {
					@Override
					public void invoke() throws Throwable {
						Usuario usuario = cenarioTestes.getUsuarioCarlos();
						dao.persist(usuario);
						dao.flush();
						List<Usuario> usuarios = dao.findAllByClassName("usuario");
						Assert.assertTrue(usuarios.contains(usuario));
						Usuario usuarioBD = dao.findByEntityId(Usuario.class, usuario.getId());
						Assert.assertEquals(usuario, usuarioBD);
					}
				});
			}
		});

	}

	@Test
	public void testEventosCriadosPeloUsuario() {
		Helpers.running(Helpers.fakeApplication(Helpers.inMemoryDatabase()), new Runnable() {
			public void run() {
				JPA.withTransaction(new play.libs.F.Callback0() {
					@Override
					public void invoke() throws Throwable {
						Usuario joana = cenarioTestes.getUsuarioJoana();
						dao.persist(joana);
						dao.flush();
						Viagem viagem = cenarioTestes.getViagemBrasil();
						viagem.setResponsavel(joana);
						viagem.setInscricaoStrategy(new InscricaoAberta());
						dao.persist(viagem);
						dao.flush();
						Viagem viagemBD = dao.findByEntityId(Viagem.class, 
								viagem.getId());
						Assert.assertEquals(viagemBD.getResponsavel(), joana);
					}
				});
			}
		});
	}
	
	@Test
	public void testLogin() {
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
    	            	callAction(controllers.routes.ref.Application.cadastrarUsuario(), 
    	                		fakeRequest().withFormUrlEncodedBody(form));
    	            	
    	            	Result result = callAction(controllers.routes.ref.Login.authenticate(), 
    	                		fakeRequest().withFormUrlEncodedBody(form));
    	            	assertThat(!session(result).isEmpty());
    	            	
    	            	List<Usuario> usuarios = dao.findAllByClassName("usuario");
    	                assertThat(session(result).get("user"))
    	                	.isEqualTo(usuarios.get(0).getId().toString());
					}
				});
			}
		});
	}
	
}
