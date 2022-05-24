package br.com.spring.fastlog.domain.service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.spring.fastlog.domain.exception.NegocioException;
import br.com.spring.fastlog.domain.model.Cliente;
import br.com.spring.fastlog.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service // Regras de negócio
public class CatalogoClienteService {

	private ClienteRepository clienteRepository;

	@Transactional		//ou tudo ou nada
	public Cliente salvar(Cliente cliente) {

		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())		//.isPresent() ? true : false ;
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
				
		if (emailEmUso) {

			throw new NegocioException("Email já existe");
		}

		return clienteRepository.save(cliente);
	}

	public void excluir(Long clienteId) {

		clienteRepository.deleteById(clienteId);
	}
	
	public Cliente buscar(Long clienteId) {
		
		return  clienteRepository.findById(clienteId)
				.orElseThrow(() -> new NegocioException("Cliente não encontrado!"));
	}

	
}
