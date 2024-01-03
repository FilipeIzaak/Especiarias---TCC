package br.com.log.demo.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.log.demo.model.Carrinho;
import br.com.log.demo.model.User;


public interface CarrinhoService  extends JpaRepository<Carrinho, Long>{
	List<Carrinho> findByUser(User user);

}
