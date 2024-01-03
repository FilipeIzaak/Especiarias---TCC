package br.com.log.demo.web;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.log.demo.model.Role;
import br.com.log.demo.model.User;
import br.com.log.demo.repository.UserRepository;
import br.com.log.demo.service.UserService;


@Controller

public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping("/especiarias/login")
	public String login() {
		return "/visao/pagLogin";
	}
	
	@GetMapping("/especiarias/living-room")
	public String livingRoom() {
		String home = "redirect:/especiarias/index";
		
		User user = userService.getAuthenticatedUser();
		
		String principalRole = user.getPrincipalRole();
		Collection<Role> roles = user.getRoles();
		
		for(Role r :  roles) {
			if(r.getName().equals("ROLE_ADMIN") && principalRole.equals("ROLE_ADMIN")) {
				home = "redirect:/especiarias/gerenciamento";
			}else if(r.getName().equals("ROLE_USER") && principalRole.equals("ROLE_USER")) {
				home = "redirect:/especiarias/users/inicio";
			}
			
		}

		return home;
	}
	
	
	
	@GetMapping("/especiarias/users/inicio")
	public String homeUser(Model model) {
		
		String home = "/visao/pagInicial";
		User user = userService.getAuthenticatedUser();
		String username = user.getEmail();
		model.addAttribute("username", username);
		
		return home;
	}
	
	
	
	// controller do gerenciamento do user
	

    @GetMapping("/especiarias/gerenciamento/user")
    public String listUsers(Model model) {
        // Obtenha a lista de todos os usuários do repositório
        Iterable<User> users = userRepository.findAll();

        // Adicione a lista de usuários ao modelo para ser exibida na página
        model.addAttribute("users", users);

        // Retorne o nome da página que exibirá a lista de usuários (por exemplo, listUsers.html)
        return "/gerenciamento/gerenciadoruser";
    }

    @GetMapping("/especiarias/gerenciamento/users/{id}")
    public String UserDetails(@PathVariable("id") Long id, Model model) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            model.addAttribute("user", optionalUser.get());
            return "/detalhe/detalheCliente";
        } else {
            return "usernaoexiste";
        }
        
    }
    @GetMapping("/especiarias/gerenciamento/users/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "User excluído com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("message", "Usuário não encontrado para exclusão.");
        }
        return "/excluidor/excluiuuser";
    }

}

	