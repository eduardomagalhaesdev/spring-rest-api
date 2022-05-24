package br.com.spring.fastlog.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.spring.fastlog.domain.model.Cliente;
import br.com.spring.fastlog.domain.repository.ClienteRepository;
import br.com.spring.fastlog.domain.service.CatalogoClienteService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	// @Autowired //instancia injetada gerida pelo Spring
	private ClienteRepository clienteRepository;
	private CatalogoClienteService catalogoClienteService;

	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}

	@GetMapping("/pornome")
	public List<Cliente> porNome() {
		return clienteRepository.findByNomeContaining("o");
	}

	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> findById(@PathVariable Long clienteId) {
		/*return clienteRepository.findById(clienteId)
		 * .map(cliente -> ResponseEntity.ok(cliente))
		 * .map(ResponseEntity::ok)
		 * .orElse(ResponseEntity.notFound().build());*/
		   
		
		

		// if (!clienteRepository.findById(clienteId).isEmpty()) {

		Optional<Cliente> cliente = clienteRepository.findById(clienteId);

		if (!cliente.isEmpty()) {

			return ResponseEntity.ok(cliente.get());
		}

		return ResponseEntity.notFound().build();

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@RequestBody @Valid Cliente cliente) {

		return catalogoClienteService.salvar(cliente);
	}

	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @RequestBody Cliente cliente) {

		if (clienteRepository.existsById(clienteId)) {

			cliente.setId(clienteId);
			catalogoClienteService.salvar(cliente);
			return ResponseEntity.ok(cliente);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> excluir(@PathVariable Long clienteId) {
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}

		catalogoClienteService.excluir(clienteId);
		return ResponseEntity.noContent().build();
	}

}