package br.com.log.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.log.demo.model.Carrinho;
import br.com.log.demo.model.User;


public interface CarrinhoRepository extends JpaRepository <Carrinho, Long> {
	
	
	@Query(value = "SELECT * FROM carrinhos c WHERE c.user_id=?", nativeQuery = true)
	List<Carrinho> findByUser(User user);
}
