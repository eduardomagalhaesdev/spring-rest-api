package br.com.spring.fastlog.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.spring.fastlog.domain.model.Cliente;

@Repository // Componente do Spring(instancias geradas pelo próprio container do Spring) que
			// gerencia as entidades.
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	/*Repository é uma classe que tem como responsabilidade implementar métodos que 
	 * fazem as operações de persistencia de dados */
	
	
	List<Cliente> findByNome(String nome);
	List<Cliente> findByNomeContaining(String nome);
	Optional<Cliente> findByEmail(String email);
	
}
