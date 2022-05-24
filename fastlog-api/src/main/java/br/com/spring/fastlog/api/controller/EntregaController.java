package br.com.spring.fastlog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.spring.fastlog.domain.model.Entrega;
import br.com.spring.fastlog.domain.repository.EntregaRepository;
import br.com.spring.fastlog.domain.service.SolicitacaoEntregaService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private SolicitacaoEntregaService solicitacaoEntregaService;
	private EntregaRepository entregaRepository;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Entrega solicitarEntrega(@Valid @RequestBody Entrega entrega) {
		
		return solicitacaoEntregaService.solicitarEntrega(entrega);
	}
	
	@GetMapping
	public List<Entrega> listar(){
		
		return entregaRepository.findAll();
	}
	
	@GetMapping("/{entregaId}")
	public ResponseEntity<Entrega> buscar(@PathVariable Long entregaId){
		
		return entregaRepository.findById(entregaId)
				.map(ResponseEntity :: ok)
				.orElse(ResponseEntity.notFound().build());
	}
}
