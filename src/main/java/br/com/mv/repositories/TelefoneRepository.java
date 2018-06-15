package br.com.mv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mv.model.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long>{

	
	
}
