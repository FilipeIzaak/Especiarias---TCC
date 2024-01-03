package br.com.log.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.log.demo.model.Endereco;
import br.com.log.demo.repository.EnderecoRepository;

@Service
public abstract class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Override
    public Endereco findByCep(String cep) {
        // Implemente a lógica para buscar um usuário pelo email no banco de dados
        // Você pode chamar o método correspondente do UserRepository
        return enderecoRepository.findByCep(cep);
    }

    // Implemente outros métodos definidos na interface UserService, se necessário
}
