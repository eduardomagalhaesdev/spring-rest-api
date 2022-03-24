package br.com.spring.fastlog.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.spring.fastlog.domain.model.Cliente;
import br.com.spring.fastlog.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class ClienteController {

	//@Autowired	//instancia injetada gerida pelo Spring
	
	
	private ClienteRepository clienteRepository;
	
	
	
	
	@GetMapping("/clientes")
	public List<Cliente> listar(){
		return clienteRepository.findAll();
	}
	
	@GetMapping("/clientes/pornome")
	public List<Cliente> porNome(){
		return clienteRepository.findByNomeContaining("o");
	}
	
	@GetMapping("/clientes/{clienteId}")
	public ResponseEntity<Cliente> findById(@PathVariable Long clienteId) {
		
		//if (!clienteRepository.findById(clienteId).isEmpty()) {
			
			Optional<Cliente> cliente = clienteRepository.findById(clienteId); 
		
			if (!cliente.isEmpty()) {
				
				return ResponseEntity.ok(cliente.get());
			}
			
				return ResponseEntity.notFound().build();
			
			
	} 

}
