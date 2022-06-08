package br.com.spring.fastlog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.spring.fastlog.api.assembler.EntregaAssembler;
import br.com.spring.fastlog.api.model.EntregaDto;
import br.com.spring.fastlog.api.model.input.EntregaInput;
import br.com.spring.fastlog.domain.model.Entrega;
import br.com.spring.fastlog.domain.repository.EntregaRepository;
import br.com.spring.fastlog.domain.service.FinalizacaoEntregaService;
import br.com.spring.fastlog.domain.service.SolicitacaoEntregaService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private FinalizacaoEntregaService finalizacaoEntregaService;
	private SolicitacaoEntregaService solicitacaoEntregaService;
	private EntregaRepository entregaRepository;
	private EntregaAssembler assembler;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	//Uma classe na camada de domínio não deve ter conhecimento de classes na camada da api.
	
	
	public EntregaDto solicitarEntrega(@Valid @RequestBody EntregaInput entregaInput) {
		Entrega novaEntrega = assembler.toEntity(entregaInput);
		
		Entrega entregaSolicitada = solicitacaoEntregaService.solicitarEntrega(novaEntrega);
		
		return assembler.toModel(entregaSolicitada);
	}
	
	@GetMapping
	public List<EntregaDto> listar(){
		
		return assembler.toCollectionmodel(entregaRepository.findAll());
	}
	
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaDto> buscar(@PathVariable Long entregaId){
		
		return entregaRepository.findById(entregaId)
				.map(entrega -> ResponseEntity.ok(assembler.toModel(entrega))) 
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{entregaId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long entregaId) {
		finalizacaoEntregaService.finalizar(entregaId);
	}
}
