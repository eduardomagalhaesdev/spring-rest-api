package br.com.spring.fastlog.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import br.com.spring.fastlog.domain.ValidationGroups;
import br.com.spring.fastlog.domain.exception.NegocioException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Entrega {

	/*
	 * Caso todas as operações do sistema sejam realizadas apenas via api, as
	 * anotações do BeanValidation podem ser retiradas do representation model,
	 * visto que, as validações já estao ocorrendo no modelo de entrada. Csso haja
	 * operações usando outro tipo de interface, devem-se manter as validações em
	 * ambas as classes.
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@ManyToOne // Muitas ENTREGAS para um CLIENTE //Mapeamento da Foreing Key da tabela Entrega
	@NotNull
	@Valid
	@ConvertGroup(from = javax.validation.groups.Default.class, to = ValidationGroups.ClienteId.class)
	private Cliente cliente;

	@Embedded // Abstrair dados para outra tabela
	@NotNull
	@Valid
	private Destinatario destinatario;

	@NotNull
	private BigDecimal taxa;

	@OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
	private List<Ocorrencia> ocorrencias;

	@JsonProperty(access = Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	private StatusEntrega status;

	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime dataInicio;

	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataFim;

	public Ocorrencia adicionarOcorrencia(String descricao) {

		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setDescricao(descricao);
		ocorrencia.setDataRegistro(OffsetDateTime.now());
		ocorrencia.setEntrega(this);

		this.getOcorrencias().add(ocorrencia);

		return ocorrencia;
	}

	public void finalizar(Entrega entrega) {

		if (naoPodeSerFinalizada()) {
			throw new NegocioException("Entrega nao pode ser finalizada.");
		}
		
		setStatus(StatusEntrega.FINALIZADO);
		setDataFim(OffsetDateTime.now());
	}
	
	public boolean podeSerFinalizada() {
		return StatusEntrega.PENDENTE.equals(getStatus());
	}

	public boolean naoPodeSerFinalizada() {
		return !podeSerFinalizada();
	}
}
