import static org.fest.assertions.Assertions.assertThat;

import java.util.Calendar;

import models.EContinente;
import models.Usuario;
import models.Viagem;
import models.ViagemIlimitada;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;

import org.junit.Assert;
import org.junit.Test;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

	GenericDAO dao = new GenericDAOImpl();
	
    @Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertThat(a).isEqualTo(2);
    }

    @Test
    public void renderTemplate() {
        Usuario usuario = new Usuario();
        usuario.setNome("joao");
        usuario.setEmail("jose@mail.com");
        usuario.setSenha("password");
        dao.persist(usuario);
        
        Viagem viagem = new ViagemIlimitada();
        viagem.setPais("Brasil");
        viagem.setDescricao("teste");
        viagem.setDataInicio(Calendar.getInstance().getTime());
        viagem.setDataFim(Calendar.getInstance().getTime());
        dao.persist(viagem);
        
        viagem.inscreverParticipante(usuario);
        dao.merge(viagem);
        
        Viagem viagemBD = dao.findByEntityId(Viagem.class, viagem.getId());
        Assert.assertTrue(viagemBD.getParticipantes().contains(usuario));
    }


}
