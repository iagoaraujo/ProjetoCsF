package models;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

@MappedSuperclass
public abstract class InscricaoStrategy {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	public abstract boolean validaInscricao(Viagem viagem, String senha);
	
	public abstract boolean exigeSenha();
	
}
