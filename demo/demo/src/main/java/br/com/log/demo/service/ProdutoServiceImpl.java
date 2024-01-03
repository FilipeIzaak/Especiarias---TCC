package br.com.log.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.log.demo.model.Produto;
import br.com.log.demo.repository.ProdutoRepository;

@Service
public abstract class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public Produto save(Produto produto) {

		return produtoRepository.save(produto);
	}

	@Override
	public List<Produto> findAll() {

		return produtoRepository.findAll();
	}
}
