import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;

import java.util.List;

import models.Usuario;
import models.Viagem;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import play.db.jpa.JPA;
import play.db.jpa.Transactional;
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
						Usuario carlos = cenarioTestes.getUsuarioCarlos();
						dao.persist(carlos);
						dao.flush();
						Viagem viagem = cenarioTestes.getViagemBrasil();
						viagem.setResponsavel(carlos);
						dao.persist(viagem);
						dao.flush();
						Assert.assertEquals(viagem.getResponsavel(), carlos);
						carlos = dao.findByEntityId(Usuario.class, carlos.getId());
						List<Viagem> viagens = carlos.getViagensCriadas();
						System.out.println(viagens.size());
					}
				});
			}
		});
	}
}
