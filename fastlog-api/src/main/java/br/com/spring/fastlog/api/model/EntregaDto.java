package br.com.spring.fastlog.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import br.com.spring.fastlog.domain.model.StatusEntrega;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaDto {

	private Long id;
	private ClienteResumoModel cliente;
	private DestinatarioDto nomeDestinatario;
	private BigDecimal taxa;
	private StatusEntrega status;
	private OffsetDateTime DataInicio;
	private OffsetDateTime DataFim;


}
