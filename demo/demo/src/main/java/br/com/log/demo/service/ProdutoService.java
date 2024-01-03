package br.com.log.demo.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.log.demo.model.Produto;

public interface ProdutoService  extends JpaRepository<Produto, Long>{
	Produto save(Produto produto);
	List<Produto> findAll();

}