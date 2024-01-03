package br.com.log.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.log.demo.model.Produto;

public interface ProdutoRepository extends JpaRepository <Produto, Long> {
	Produto findByDescricao(String descricao); 

}

