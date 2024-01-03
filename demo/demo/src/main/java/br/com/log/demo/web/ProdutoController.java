package br.com.log.demo.web;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.log.demo.model.Produto;
import br.com.log.demo.repository.ProdutoRepository;
import br.com.log.demo.service.ProdutoService;
import br.com.log.demo.service.UserService;

@Controller
@RequestMapping("/especiarias")
public class ProdutoController {

	@Autowired
    private ProdutoService produtoService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private ProdutoRepository produtoRepository;

    
	@GetMapping("/api/produtos")

	@ResponseBody // Indica que a resposta será em formato JSON

	public List<Produto> getProdutos() {

	    return produtoRepository.findAll(); // Retorna a lista de produtos do seu repositório

	}

    @GetMapping("/gerenciamento/cadastrarproduto")
    public String showNewUserForm(Model model, Produto produto) {
        model.addAttribute("produto", new Produto());
        return "/cadastros/produtocad";
    }

    @PostMapping("/gerenciamento/cadastrarproduto")
    public String criaProduto(@RequestParam(value = "file", required = false) MultipartFile file,
                              @ModelAttribute("produto") Produto produto, BindingResult result) {

        try {
            // Verifica se o arquivo é uma imagem
            if (file != null && !file.isEmpty() && file.getContentType().startsWith("image")) {
                produto.setFoto(file.getBytes());
            } else {
                produto.setFoto(null);
            }

            // Salva o produto
            produtoService.save(produto);

        } catch (IOException e) {
            // Lida com exceções de IO de forma adequada (pode redirecionar para uma página de erro)
            e.printStackTrace(); // Aqui seria mais adequado logar a exceção
        }

        return "redirect:/especiarias/gerenciamento/produto";
    }


    @GetMapping("/gerenciamento/produto")
    public String listProdutos(Model model) {
        // Obtenha a lista de todos os produtos do repositório
        Iterable<Produto> produtos = produtoRepository.findAll();

        // Adicione a lista de produtos ao modelo para ser exibida na página
        model.addAttribute("produtos", produtos);

        // Retorne o nome da página que exibirá a lista de produtos
        return "/gerenciamento/gerenciadorprod";
    }


    @GetMapping("/gerenciamento/produto/{id}")
    public String ProdutoDetails(@PathVariable("id") Long id, Model model) {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);
        if (optionalProduto.isPresent()) {
            model.addAttribute("produto", optionalProduto.get());
            return "/detalhe/detalheProduto";
        } else {
            return "/excluidor/excluiproduto";
        }
        
    }
    @GetMapping("/gerenciamento/produto/{id}/delete")
    public String deleteProduto(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);
        if (optionalProduto.isPresent()) {
            produtoRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "User excluído com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("message", "Usuário não encontrado para exclusão.");
        }
        return "/excluidor/excluiproduto";
    }
}

