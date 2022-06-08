package br.com.spring.fastlog.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DestinatarioDto {

	private String nome;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
}
