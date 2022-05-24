package br.com.spring.fastlog.domain.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.spring.fastlog.domain.model.Cliente;
import br.com.spring.fastlog.domain.model.Entrega;
import br.com.spring.fastlog.domain.model.StatusEntrega;
import br.com.spring.fastlog.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

	private EntregaRepository entregaRepository;
	//private ClienteRepository clienteRepository;
	private CatalogoClienteService catalogoClienteService;
	
	@Transactional
	public Entrega solicitarEntrega(Entrega entrega) {

		Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());
		
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataInicio(LocalDateTime.now());
		
		return entregaRepository.save(entrega);
	}
}
