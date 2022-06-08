package br.com.spring.fastlog.domain.service;

import org.springframework.stereotype.Service;

import br.com.spring.fastlog.api.exception.EntidadeNaoEncontradaException;
import br.com.spring.fastlog.domain.exception.NegocioException;
import br.com.spring.fastlog.domain.model.Entrega;
import br.com.spring.fastlog.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

	private EntregaRepository entregaRepository;

	public Entrega buscar(Long entregaId) {
		return entregaRepository.findById(entregaId)

				.orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada!"));

	}
}
