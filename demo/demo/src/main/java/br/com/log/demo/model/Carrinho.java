package br.com.log.demo.model;


import java.time.LocalDateTime;
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

//import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "carrinhos")
public class Carrinho {
	
	
	public Carrinho() {
		
		
	}
	
	public Carrinho(Long id, double valor, LocalDateTime dataCompra, String statusCarrinho,
			List<ItemCarrinho> itensCarrinho, User user) {
		this.id = id;
		this.valor = valor;
		this.dataCompra = dataCompra;
		this.statusCarrinho = statusCarrinho;
		this.itensCarrinho = itensCarrinho;
		this.user = user;
	}




	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double valor;
	
	@Column (name="data_compra")
	@CreationTimestamp
	private LocalDateTime dataCompra;
	
	@Column (name="status_carrinho")
	private String statusCarrinho;
	
	
	
	//@OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL, orphanRemoval = true)
	@OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL)
	private List<ItemCarrinho> itensCarrinho = new ArrayList<ItemCarrinho>();
	
	
	@ManyToOne
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@JoinColumn(name="user_id")
	@JsonIgnore
	private User user;

	


	public void setItensCarrinho(List<ItemCarrinho> itensCarrinho) {
		double valorTotalCarrinho = 0;
		
		if (itensCarrinho != null) {
		// Buscar lista de Itens de itens na Sessão e calcular valor do carrinho
		for(int i = 0; i < itensCarrinho.size(); i++) {
			valorTotalCarrinho += itensCarrinho.get(i).getPreco() * 
					itensCarrinho.get(i).getQuantidade() -
					itensCarrinho.get(i).getDesconto();
		    }
		}
		this.valor = valorTotalCarrinho;
		
		this.itensCarrinho = itensCarrinho;
	}


	


	

	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public double getValor() {
		return valor;
	}




	public void setValor(double valor) {
		this.valor = valor;
	}




	public LocalDateTime getDataCompra() {
		return dataCompra;
	}




	public void setDataCompra(LocalDateTime dataCompra) {
		this.dataCompra = dataCompra;
	}




	public String getStatusCarrinho() {
		return statusCarrinho;
	}




	public void setStatusCarrinho(String statusCarrinho) {
		this.statusCarrinho = statusCarrinho;
	}




	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
	}




	public List<ItemCarrinho> getItensCarrinho() {
		return itensCarrinho;
	}

	
	
}