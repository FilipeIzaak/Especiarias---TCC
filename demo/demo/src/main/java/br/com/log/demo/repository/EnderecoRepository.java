package br.com.log.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.log.demo.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository <Endereco, Long> {
	Endereco findByCep(String cep);

}
