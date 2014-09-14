package models.utils;

import java.util.ArrayList;
import java.util.List;

import models.EContinente;
import models.InscricaoAberta;
import models.InscricaoLimitada;
import models.InscricaoStrategy;
import models.Usuario;
import models.Viagem;

public class Utils {

	public static List<Viagem> getListaViagensNaoInscritasDoUsuario(Usuario usuario,
			List<Viagem> viagens) {
		List<Viagem> listaFiltrada = new ArrayList<Viagem>();
		for (Viagem viagem: viagens) {
			if (!viagem.getParticipantes().contains(usuario)) {
				listaFiltrada.add(viagem);
			}
		}
		return listaFiltrada;
	}
	
	public static InscricaoStrategy getInstanciaInscricaoStrategy(String tipo) {
		if (tipo.equals("aberta")) {
			return new InscricaoAberta();
		}
		return new InscricaoLimitada();
	}

	public static List<Viagem> filtraViagensPorContinente(EContinente eContinente,
			List<Viagem> viagens) {
		List<Viagem> listaFiltrada = new ArrayList<Viagem>();
		for (Viagem viagem: viagens) {
			if (viagem.getContinente().equals(eContinente)) {
				listaFiltrada.add(viagem);
			}
		}
		return listaFiltrada;
	}
}
