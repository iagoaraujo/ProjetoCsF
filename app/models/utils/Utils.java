package models.utils;

import java.util.ArrayList;
import java.util.List;

import models.EContinente;
import models.InscricaoAberta;
import models.InscricaoLimitada;
import models.InscricaoStrategy;
import models.Viagem;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;

public class Utils {

	private static GenericDAO dao = new GenericDAOImpl();
	
	public static InscricaoStrategy getInstanciaInscricaoStrategy(String tipo) {
		if (tipo.equals("aberta")) {
			return new InscricaoAberta();
		}
		return new InscricaoLimitada();
	}

	public static List<Viagem> filtraViagensPorContinente(EContinente eContinente) {
		List<Viagem> viagens = getDao().findAllByClassName("viagem");
		List<Viagem> listaFiltrada = new ArrayList<Viagem>();
		for (Viagem viagem: viagens) {
			if (viagem.getContinente().equals(eContinente)) {
				listaFiltrada.add(viagem);
			}
		}
		return listaFiltrada;
	}
	
	private static GenericDAO getDao() {
		return dao;
	}
}
