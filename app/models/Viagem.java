package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.MetaValue;

@Entity(name="viagem")
public class Viagem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private EContinente continente;
	
	@Column
	@NotNull
	private String local;
	
	@Column
	@NotNull
	private String dataInicio;
	
	@Column
	@NotNull
	private String dataFim;
	
	@Column
	@NotNull
	private String descricao;
	
	@ManyToMany(cascade=CascadeType.ALL)
	private List<Usuario> participantes;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Usuario responsavel; 
	
	@Column
	private String senha;
	
	@Any(metaColumn = @Column(name = "TIPO_INSCRICAO"))
    @AnyMetaDef(idType = "long", metaType = "string", 
            metaValues = { 
             @MetaValue(targetEntity = InscricaoAberta.class, value = "A"),
             @MetaValue(targetEntity = InscricaoLimitada.class, value = "L"),
       })
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name="VIAGEM_ID")
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

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
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
	
	public void addParticipante(Usuario usuario) {
		if (!participantes.contains(usuario)) {
			participantes.add(usuario);
		}
	}
	
}
