package br.com.mv.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.mv.dto.PessoaDTO;
import br.com.mv.exception.DataIntegrityException;
import br.com.mv.exception.ObjectNotFoundException;
import br.com.mv.model.Pessoa;
import br.com.mv.model.Telefone;
import br.com.mv.repositories.PessoaRepository;
import br.com.mv.repositories.TelefoneRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepo;
	
	@Autowired
	private TelefoneRepository telefoneRepo;
	
	public Pessoa find(Long id) {			
		
		Optional<Pessoa> pessoa = pessoaRepo.findById(id);
		
		if(pessoa == null) {
			
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		
		return pessoa.get();
	}

	public Pessoa insert(Pessoa pessoa) {

		pessoa.setId(null);
		
		pessoa = pessoaRepo.save(pessoa);
		
		List<Telefone> telefones = pessoa.getTelefones();	
		
		for(Telefone telefone: pessoa.getTelefones()){
			telefone.setPessoa(pessoa);			
		}
		
		telefoneRepo.saveAll(telefones);
		
		return pessoa;
		
	}
	
	public Pessoa update(Pessoa obj) {
		
		Pessoa cliente = find(obj.getId());
					
		pessoaRepo.save(cliente);			
		
		return pessoaRepo.save(obj);
	}
	
	public void delete (Long id) {
		
		try {			
			
			Pessoa pessoa = find(id);
			
			for(Telefone telefone: pessoa.getTelefones()) {
				
				telefoneRepo.deleteById(telefone.getId());
			}
			
			pessoaRepo.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			
			throw new DataIntegrityException("Não é possível excluir porque há telefones relacionados"); 
		}
	}
	
	public List<Pessoa> findAll() {		
		
		return pessoaRepo.findAll();
	}
	
	public Page<Pessoa> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return pessoaRepo.findAll(pageRequest);
	
	}
	
	
	public Pessoa fromDTO (PessoaDTO pessoaDTO) {
		
		return new Pessoa(pessoaDTO.getId(), pessoaDTO.getNome(), pessoaDTO.getCpf(), pessoaDTO.getDataNascimento(), pessoaDTO.getEmail(), pessoaDTO.getTelefones());
	}

}
