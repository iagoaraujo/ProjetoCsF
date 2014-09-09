package models;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class InscricaoStrategy {

	public abstract boolean validaInscricao(Viagem viagem, String senha);
	
	public abstract boolean exigeSenha();
	
}
