import java.util.Calendar;

import models.EContinente;
import models.Usuario;
import models.Viagem;


public class CenarioTestes {

	private final String PASSWORD = "password";
	private final String DATA = Calendar.getInstance().getTime().toString();
	
	private Usuario usuarioJoao;
	private Usuario usuarioCarlos;
	private Usuario usuarioJoana;
	
	private Viagem viagemEspanha;
	private Viagem viagemEgito;
	private Viagem viagemRussia;
	private Viagem viagemAustralia;
	private Viagem viagemBrasil;
	private Viagem viagemCanada;
	
	public CenarioTestes() {
		usuarioCarlos = new Usuario();
		usuarioJoana = new Usuario();
		usuarioJoao = new Usuario();
		
		viagemEspanha = new Viagem();
		viagemEgito = new Viagem();
		viagemRussia = new Viagem();
		viagemAustralia = new Viagem();
		viagemBrasil = new Viagem();
		viagemCanada = new Viagem();
		
		configuraUsuarios();
		configuraViagens();
	}

	private void configuraViagens() {
		viagemAustralia.setContinente(EContinente.OCEANIA);
		viagemAustralia.setDataFim(DATA);
		viagemAustralia.setDataInicio(DATA);
		viagemAustralia.setLocal("Australia");
		viagemAustralia.setDescricao(getDescricao(viagemAustralia.getLocal()));
		
		viagemEspanha.setContinente(EContinente.EUROPA);
		viagemEspanha.setDataFim(DATA);
		viagemEspanha.setDataInicio(DATA);
		viagemEspanha.setLocal("Espanha");
		viagemEspanha.setDescricao(getDescricao(viagemAustralia.getLocal()));
		
		viagemEgito.setContinente(EContinente.AFRICA);
		viagemEgito.setDataFim(DATA);
		viagemEgito.setDataInicio(DATA);
		viagemEgito.setLocal("Egito");
		viagemEgito.setDescricao(getDescricao(viagemEgito.getLocal()));
		
		viagemRussia.setContinente(EContinente.ASIA);
		viagemRussia.setDataFim(DATA);
		viagemRussia.setDataInicio(DATA);
		viagemRussia.setLocal("Russia");
		viagemRussia.setDescricao(getDescricao(viagemRussia.getLocal()));
		
		viagemBrasil.setContinente(EContinente.AMERICA_LATINA);
		viagemBrasil.setDataFim(DATA);
		viagemBrasil.setDataInicio(DATA);
		viagemBrasil.setLocal("Brasil");
		viagemBrasil.setDescricao(getDescricao(viagemBrasil.getLocal()));
		
		viagemCanada.setContinente(EContinente.AMERICA_DO_NORTE);
		viagemCanada.setDataFim(DATA);
		viagemCanada.setDataInicio(DATA);
		viagemCanada.setLocal("Canada");
		viagemCanada.setDescricao(getDescricao(viagemCanada.getLocal()));
	}

	public Usuario getUsuarioJoao() {
		return usuarioJoao;
	}

	public Usuario getUsuarioCarlos() {
		return usuarioCarlos;
	}

	public Usuario getUsuarioJoana() {
		return usuarioJoana;
	}

	public Viagem getViagemEspanha() {
		return viagemEspanha;
	}

	public Viagem getViagemEgito() {
		return viagemEgito;
	}

	public Viagem getViagemRussia() {
		return viagemRussia;
	}

	public Viagem getViagemAustralia() {
		return viagemAustralia;
	}

	public Viagem getViagemBrasil() {
		return viagemBrasil;
	}

	public Viagem getViagemCanada() {
		return viagemCanada;
	}

	private void configuraUsuarios() {
		usuarioJoana.setEmail("joana@mail.com");
		usuarioJoana.setNome("Joana");
		usuarioJoana.setSenha(PASSWORD);
		
		usuarioCarlos.setEmail("carlos@mail.com");
		usuarioCarlos.setNome("Carlos");
		usuarioCarlos.setSenha(PASSWORD);
		
		usuarioJoao.setEmail("joao@mail.com");
		usuarioJoao.setNome("Joao");
		usuarioJoao.setSenha(PASSWORD);
	}
	
	private String getDescricao(String local) {
		return "A viagem será no seguinte local: " + local;
	}
}
