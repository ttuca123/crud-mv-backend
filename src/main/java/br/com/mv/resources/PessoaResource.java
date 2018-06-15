package br.com.mv.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.mv.dto.PessoaDTO;
import br.com.mv.exception.DataIntegrityException;
import br.com.mv.model.Pessoa;
import br.com.mv.services.PessoaService;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaResource {
	
	@Autowired
	PessoaService pessoaService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Long id) {

		
		Pessoa pessoa = pessoaService.find(id);
		
		return ResponseEntity.ok(pessoa);
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody PessoaDTO pessoaDTO) {
		
		Pessoa pessoa = pessoaService.fromDTO(pessoaDTO);

		
		pessoa = pessoaService.insert(pessoa);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pessoaDTO).toUri();

		return ResponseEntity.created(uri).build();
		
		
	}
	
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@Valid @RequestBody PessoaDTO pessoaDTO) {
		
		Pessoa pessoa = pessoaService.fromDTO(pessoaDTO);
		
		pessoa = pessoaService.update(pessoa);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pessoaDTO).toUri();

		return ResponseEntity.created(uri).build();
		
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Pessoa> delete(@PathVariable Long id) {

		
		try {
			pessoaService.delete(id);

		} catch (DataIntegrityViolationException e) {

			throw new DataIntegrityException("Não é possível excluir uma Pessoa que possui telefones");
		}

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PessoaDTO>> findAll() {

		List<Pessoa> pessoas = pessoaService.findAll();

		List<PessoaDTO> list = pessoas.stream().map(obj -> new PessoaDTO(obj)).collect(Collectors.toList());

		return ResponseEntity.ok().body(list);

	}
	
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<PessoaDTO>> findPage(
				@RequestParam(value="page", defaultValue="0") Integer page,
				@RequestParam(value="linesPerPage", defaultValue="5") Integer linesPerPage,
				@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
				@RequestParam(value="direction", defaultValue="ASC") String direction
			) {
		
		Page<Pessoa> list = pessoaService.findPage(page, linesPerPage, orderBy, direction);
		
		Page<PessoaDTO> listDTO = list.map( obj -> new PessoaDTO(obj));		

		return ResponseEntity.ok().body(listDTO);

	}
}
