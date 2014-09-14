package models;

import javax.persistence.Entity;

@Entity
public class InscricaoLimitada extends InscricaoStrategy{

	@Override
	public boolean validaInscricao(Viagem viagem, String senha) {
		System.out.println("senha enviada: " + senha);
		System.out.println("senha viagem: " + viagem.getSenha());
		if (viagem.getSenha()==null) {
			throw new InscricaoException("Viagem precisa ter senha para ser limitada.");
		}
		if (senha.equals(viagem.getSenha())) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean exigeSenha() {
		return true;
	}
}
