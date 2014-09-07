package models;

import javax.persistence.Entity;

@Entity(name="viagemIlimitada")
public class ViagemIlimitada extends Viagem{

	public ViagemIlimitada() {
		super();
	}
	
	@Override
	public void inscreverParticipante(Usuario usuario) {
		if (!participantes.contains(usuario)) {
			participantes.add(usuario);
		}
	}

	@Override
	public void inscreverParticipante(Usuario usuario, String senha) {
		throw new InscricaoException("Esse tipo de viagem não exige senha");
	}

}
