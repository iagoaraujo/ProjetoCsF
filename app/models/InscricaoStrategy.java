package models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class InscricaoStrategy {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	public abstract boolean validaInscricao(Viagem viagem, String senha);
	
	public abstract boolean exigeSenha();
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
