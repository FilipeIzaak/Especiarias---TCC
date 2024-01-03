package br.com.log.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.log.demo.model.Carrinho;
import br.com.log.demo.model.User;
import br.com.log.demo.repository.CarrinhoRepository;



public abstract class CarrinhoServiceImpl implements CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Override
    public List<Carrinho>findByUser(User user) {
        // Implemente a lógica para buscar um usuário pelo email no banco de dados
        // Você pode chamar o método correspondente do UserRepository
        return carrinhoRepository.findByUser(user);
    }
}
