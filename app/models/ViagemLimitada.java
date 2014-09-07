package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity(name="viagemLimitada")
public class ViagemLimitada extends Viagem{

	@Column
	@NotNull
	private String senha;
	
	public ViagemLimitada() {
		super();
	}
	
	@Override
	public void inscreverParticipante(Usuario usuario) {
		throw new InscricaoException("É preciso informar a senha para se inscrever");
	}

	@Override
	public void inscreverParticipante(Usuario usuario, String senha) {
		if (senha.equals(this.senha)) {
			if (!participantes.contains(usuario)) {
				participantes.add(usuario);
			}
		}
		else {
			throw new InscricaoException("Senha inválida");
		}
	}

	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
