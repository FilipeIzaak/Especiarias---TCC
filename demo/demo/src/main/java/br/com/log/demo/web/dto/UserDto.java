package br.com.log.demo.web.dto;

import java.time.LocalDate;
import java.util.List;

import br.com.log.demo.model.Endereco;

public class UserDto {
	
	private Long id;
	private String nome;
	private String telefone;
	private String email;
	private String password;
	private LocalDate dataNascimento;
	
	
	private List<Endereco> enderecos;
	
	public UserDto () {
		
	}

	
	
	public UserDto(Long id, String nome, String telefone, String email, String password, LocalDate dataNascimento) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.password = password;
		this.dataNascimento = dataNascimento;
	}

	
	
	


	public UserDto(Long id, String nome, String telefone, String email, String password, LocalDate dataNascimento,
			List<Endereco> enderecos) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.password = password;
		this.dataNascimento = dataNascimento;
		this.enderecos = enderecos;
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



	public String getTelefone() {
		return telefone;
	}



	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public LocalDate getDataNascimento() {
		return dataNascimento;
	}



	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}



	public List<Endereco> getEnderecos() {
		return enderecos;
	}



	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	

}