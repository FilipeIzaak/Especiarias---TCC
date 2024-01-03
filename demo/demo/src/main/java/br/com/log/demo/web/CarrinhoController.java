package br.com.log.demo.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.log.demo.model.Carrinho;
import br.com.log.demo.model.ItemCarrinho;
import br.com.log.demo.model.Produto;
import br.com.log.demo.model.User;
import br.com.log.demo.repository.CarrinhoRepository;


@Controller
@RequestMapping("/especiarias")
public class CarrinhoController {
	
	@Autowired
    private CarrinhoRepository carrinhoRepository;
	
	
	 @GetMapping("/users/pagproduto")
	    public String NovoPedido(Model model) {
	        model.addAttribute("carrinho", new Carrinho());
	        return "/visao/pagProdutos";
	    }
	
	 @ResponseBody
		@PostMapping("/users/pagproduto")
		public Carrinho PegaInfo ( @RequestBody List<ItemCarrinho> itensCarrinho, HttpServletRequest req, HttpServletResponse res) {

			User user = new User(); // Pegar do usuário logado
			user.setId(1l);
			Carrinho carrinho = new Carrinho();
			carrinho.setUser(user);
			carrinho.setStatusCarrinho("Ativo");
			Carrinho carrinhoDb = carrinhoRepository.save(carrinho);

			for(ItemCarrinho item : itensCarrinho) {
	 
				Produto produto = new Produto();
				produto.setId(item.getId());
				item.setProduto(produto);
				item.setCarrinho(carrinhoDb);	

			}

			carrinhoDb.setItensCarrinho(itensCarrinho);

			 Carrinho carrinhoAtualizadoDb = carrinhoRepository.save(carrinhoDb);

				return carrinhoAtualizadoDb;

				//return "redirect:/especiarias/finalizapedido";

	}
	 
	 @GetMapping("/finalizapedido")
	    public String QrCode (Model model) {
			

	        // Retorne o nome da página que exibirá a lista de usuários (por exemplo, listUsers.html)
	        return "/visao/pagamento";
	    }
	
	 
	 @GetMapping("/gerenciamento/pedido")
	    public String listPedido(Model model) {
			

			User user = new User(); // Pegar do usuário logado
			user.setId(1l);
			
	        // Obtenha a lista de todos os usuários do repositório
	        List<Carrinho > carrinhos = carrinhoRepository.findByUser(user);
	        
	        System.out.println(carrinhos.size());

	        // Adicione a lista de usuários ao modelo para ser exibida na página
	        model.addAttribute("carrinhos", carrinhos);

	        // Retorne o nome da página que exibirá a lista de usuários (por exemplo, listUsers.html)
	        return "/gerenciamento/gerenciamentopedido";
	    }
	}

