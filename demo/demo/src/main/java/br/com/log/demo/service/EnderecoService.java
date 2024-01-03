package br.com.log.demo.service;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.log.demo.model.Endereco;

public interface EnderecoService extends JpaRepository<Endereco, Long>{
	Endereco findByCep(String cep);
}
