package br.com.spring.fastlog.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.spring.fastlog.domain.model.Cliente;

@Repository // Componente do Spring(instancias geradas pelo próprio container do Spring) que
			// gerencia as entidades.
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	List<Cliente> findByNome(String nome);
	List<Cliente> findByNomeContaining(String nome);

}
