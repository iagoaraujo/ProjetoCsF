package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity(name="viagem")
public class Viagem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private EContinente continente;
	
	@Column
	@NotNull
	private String pais;
	
	@Column
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date dataInicio;
	
	@Column
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date dataFim;
	
	@Column
	@NotNull
	private String descricao;
	
	@ManyToMany(cascade=CascadeType.ALL)
	private List<Usuario> participantes;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Usuario responsavel; 
	
	@Column
	private String senha;
	
	@Column
	@NotNull
	private InscricaoStrategy inscricaoStrategy;
	
	public Viagem() {
		this.participantes = new ArrayList<Usuario>();
	}
	
	public void inscreverParticipante(Usuario usuario, String senha) {
		if (inscricaoStrategy.validaInscricao(this, senha));
	}

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
	
	public Usuario getResponsavel() {
		return responsavel;
	}
	
	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public InscricaoStrategy getInscricaoStrategy() {
		return inscricaoStrategy;
	}
	
	public void setInscricaoStrategy(InscricaoStrategy inscricaoStrategy) {
		this.inscricaoStrategy = inscricaoStrategy;
	}
}
