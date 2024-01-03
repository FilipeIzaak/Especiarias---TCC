package br.com.log.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;



@Entity
@Table(name = "produtos")

public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	@Column(columnDefinition = "VARBINARY(max)")
	private byte[] foto;
	@Transient    // notação necessária para não criar a coluna no DB
	private String fotoString;
	private double preco;
	@Column (name="status_produto")
	private String statusProd;
	
	
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
	private List<ItemCarrinho> itensCarrinho = new ArrayList<ItemCarrinho>();

	@ManyToOne
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "user_id")
	private User user;
	

	public Produto() {
		
	}



	public Produto(Long id, String nome, String descricao, byte[] foto, double preco,
			String statusProd, List<ItemCarrinho> itensCarrinho) {
		
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.foto = foto;
		this.preco = preco;
		this.statusProd = statusProd;
		this.itensCarrinho = itensCarrinho;
	}



	public Produto(String nome, String descricao, double preco) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
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



	public String getDescricao() {
		return descricao;
	}



	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



	public byte[] getFoto() {
		return foto;
	}



	public void setFoto(byte[] foto) {
		this.foto = foto;
	}



	public double getPreco() {
		return preco;
	}



	public void setPreco(double preco) {
		this.preco = preco;
	}



	public String getStatusProd() {
		return statusProd;
	}



	public void setStatusProd(String statusProd) {
		this.statusProd = statusProd;
	}



	public List<ItemCarrinho> getItensCarrinho() {
		return itensCarrinho;
	}



	public void setItensCarrinho(List<ItemCarrinho> itensCarrinho) {
		this.itensCarrinho = itensCarrinho;
	}

	
	
	
}