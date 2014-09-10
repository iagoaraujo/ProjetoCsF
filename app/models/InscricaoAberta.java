package models;

import javax.persistence.Entity;

@Entity
public class InscricaoAberta extends InscricaoStrategy{

	@Override
	public boolean validaInscricao(Viagem viagem, String senha) {
		return true;
	}
	
	@Override
	public boolean exigeSenha() {
		return false;
	}
}
