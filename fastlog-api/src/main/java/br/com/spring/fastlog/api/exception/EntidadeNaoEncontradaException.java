package br.com.spring.fastlog.api.exception;

import br.com.spring.fastlog.domain.exception.NegocioException;

public class EntidadeNaoEncontradaException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String message) {
		super(message);
	}
}
