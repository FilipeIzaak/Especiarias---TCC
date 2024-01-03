package br.com.log.demo.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.log.demo.model.Endereco;
import br.com.log.demo.repository.EnderecoRepository;


@Controller
@RequestMapping("/especiarias")
public class EnderecoController {

	@Autowired
    private EnderecoRepository enderecoRepository;
	

    @GetMapping("/cadastrarendereco")
    public String showNewEnderecoForm(Model model) {
        model.addAttribute("endereco", new Endereco());
        return "/cadastros/enderecoCad";
    }
    

    @PostMapping("/cadastrarendereco")
    public String criaEndereco(@ModelAttribute("endereco") Endereco endereco, RedirectAttributes redirectAttributes) {
        enderecoRepository.save(endereco);
        redirectAttributes.addFlashAttribute("message", "Endereco criado com sucesso!");
        return "redirect:/especiarias/login";
    }
    

    @GetMapping("/gerenciamento/endereco")
    public String listEnderecos(Model model) {
        // Obtenha a lista de todos os usuários do repositório
        Iterable<Endereco> enderecos = enderecoRepository.findAll();

        // Adicione a lista de usuários ao modelo para ser exibida na página
        model.addAttribute("enderecos", enderecos);

        // Retorne o nome da página que exibirá a lista de endereços (por exemplo, listUsers.html)
        return "/gerenciamento/gerenciamentoEndereco";
    }

    @GetMapping("/gerenciamento/endereco/{id}")
    public String EnderecoDetails(@PathVariable("id") Long id, Model model) {
        Optional<Endereco> optionalEndereco = enderecoRepository.findById(id);
        if (optionalEndereco.isPresent()) {
            model.addAttribute("endereco", optionalEndereco.get());
            return "/detalhe/detalheEndereco";
        } else {
            return "";
        }
    }
    
}
    
