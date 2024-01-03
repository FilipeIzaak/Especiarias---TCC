package br.com.log.demo.model;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "item_carrinho")
public class ItemCarrinho implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "carrinho_id")
    @JsonIgnore
	private Carrinho carrinho;
	
	
	@ManyToOne
	@JoinColumn(name = "produto_id")
	@JsonIgnore
	private Produto produto;
	
	private int quantidade;
	@Column (name="preco")
	private double preco;
	
	private double desconto;

	public ItemCarrinho() {
		
		
	}
	
	
	public ItemCarrinho( Produto produto,int quantidade, double preco, double desconto) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.preco = preco;
		this.desconto = desconto;
	}

	public ItemCarrinho( Produto produto,  int quantidade, double preco, double desconto, Carrinho carrinho) {
		this.produto = produto;
		this.carrinho = carrinho;
		this.quantidade = quantidade;
		this.preco = preco;
		this.desconto = desconto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPrecoUnitario(double preco) {
		this.preco = preco;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}