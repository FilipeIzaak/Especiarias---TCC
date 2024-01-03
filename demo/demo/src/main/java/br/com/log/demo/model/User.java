package br.com.log.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name="users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
	
	// private   : Acesso dentro da própria classe
	// public    : Acesso livre à todas as classes
	// protected : Acesso liberado para as classes filhas (Herança)
	
	// GenerationType.AUTO      
	// valor padrão (deixa para o provedor escolher a estratégia mais adequada)	
	// GenerationType.SEQUENCE
	// Informamos ao provedor a sequência a ser seguida, caso não o provedor escolherá
	// a sequencia.
	// GenerationType.TABLE
	// Criamos uma tabela para gerenciar as chaves primárias, não há suporte para todos
	// os provedores
	
	@Id  // PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-Incremento
	private Long id;
	private String nome;
	private String email;
	private String password;
	private String telefone;
	private LocalDate dataNascimento;
	private String principalRole;
	
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	protected List<Carrinho>carrinhos = new ArrayList<Carrinho>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Produto> produtos = new ArrayList<Produto>();
	
	
	// 1:N
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Endereco> enderecos = new ArrayList<Endereco>();
	
	
	// M:N
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
	    name="users_roles",
	    joinColumns= @JoinColumn(name="user_id", referencedColumnName = "id"),
	    inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id")
	)
	private Collection<Role> roles = new ArrayList<>();

	
	// Construtor padrão: Não possui parâmetros
	
	public User() {
		
	}
	
	
	
	public User(String nome, String email, String password, ArrayList arrayList) {
		this.nome = nome;
		this.email = email;
		this.password = password;
	}

	

	public User(Long id, String nome, String email, String password, String telefone, LocalDate dataNascimento,
			String principalRole, List<Carrinho> carrinhos, List<Endereco> enderecos, Collection<Role> roles) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.password = password;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.principalRole = principalRole;
		this.carrinhos = carrinhos;
		this.enderecos = enderecos;
		this.roles = roles;
	}



	public User(Long id, String nome, String email, String password, String telefone, LocalDate dataNascimento,
			String principalRole, Collection<Role> roles) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.password = password;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.principalRole = principalRole;
		this.roles = roles;
	}



	public User(Long id, String nome, String email, String password, String telefone, LocalDate dataNascimento,
			String principalRole, List<Endereco> enderecos, Collection<Role> roles) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.password = password;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.principalRole = principalRole;
		this.enderecos = enderecos;
		this.roles = roles;
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



	public String getTelefone() {
		return telefone;
	}



	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}



	public List<Carrinho> getCarrinhos() {
		return carrinhos;
	}



	public void setCarrinhos(List<Carrinho> carrinhos) {
		this.carrinhos = carrinhos;
	}



	public LocalDate getDataNascimento() {
		return dataNascimento;
	}



	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}



	public String getPrincipalRole() {
		return principalRole;
	}



	public void setPrincipalRole(String principalRole) {
		this.principalRole = principalRole;
	}



	public List<Endereco> getEnderecos() {
		return enderecos;
	}



	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}



	public Collection<Role> getRoles() {
		return roles;
	}



	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}



	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
	
	
		
}