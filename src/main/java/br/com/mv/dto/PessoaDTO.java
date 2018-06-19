package br.com.mv.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.mv.model.Pessoa;
import br.com.mv.model.Telefone;
import br.com.mv.utils.DataUtils;

public class PessoaDTO implements Serializable{
		
	private static final long serialVersionUID = 1L;

	public PessoaDTO() {
		
		
	}
	
	
	public PessoaDTO(Pessoa pessoa) {
		
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.cpf = pessoa.getCpf();
		this.dataNascimento = pessoa.getDataNascimento();
		this.email = pessoa.getEmail();
		this.telefones = pessoa.getTelefones();
		this.idade = getIdade();
	}
	
	private Long id;

	
	private String nome;

	
	private String cpf;

	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3" )	
	private Date dataNascimento;
	
	private String email;
	
	private int idade;
	
	private List<Telefone> telefones;

	public int getIdade() {			
		
		idade = DataUtils.diferencaEntreDatasEmAnos(new LocalDate(dataNascimento), new LocalDate(new Date()));
		
		return idade;
	}


	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public Date getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public List<Telefone> getTelefones() {
		return telefones;
	}


	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}


	
}
