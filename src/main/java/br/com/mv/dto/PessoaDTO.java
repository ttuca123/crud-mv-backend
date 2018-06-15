package br.com.mv.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.mv.model.Pessoa;
import br.com.mv.model.Telefone;

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
	}
	
	private Long id;

	
	private String nome;

	
	private String cpf;

	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
	private Date dataNascimento;

	
	private String email;

	
	private List<Telefone> telefones;
	
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
