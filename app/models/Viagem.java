package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class Viagem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	@Enumerated(EnumType.STRING)
	protected EContinente continente;
	
	@Column
	@NotNull
	protected String pais;
	
	@Column
	@Temporal(TemporalType.DATE)
	@NotNull
	protected Date dataInicio;
	
	@Column
	@Temporal(TemporalType.DATE)
	@NotNull
	protected Date dataFim;
	
	@Column
	@NotNull
	protected String descricao;
	
	@ManyToMany(cascade=CascadeType.ALL)
	protected List<Usuario> participantes;
	
	public Viagem() {
		this.participantes = new ArrayList<Usuario>();
	}
	
	public abstract void inscreverParticipante(Usuario usuario);
	
	public abstract void inscreverParticipante(Usuario usuario, String senha);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EContinente getContinente() {
		return continente;
	}

	public void setContinente(EContinente continente) {
		this.continente = continente;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Usuario> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Usuario> participantes) {
		this.participantes = participantes;
	}
}
