package br.com.log.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="enderecos")
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto incremento
	private Long id;
	private String logradouro;
	private String bairro;
	private String cep;
	private String cidade;
	private String estado;
	private String numero;
	private String uf;
	
	// M:1     muitos endereços para 1 usuário
		@ManyToOne
		@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
		@JoinColumn(name= "user_id")
		private User user;
		
		
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// Construtor padrão
	public Endereco() {
		
	}

	// Getter's e Setter's
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	
	
	
	

}