package br.com.log.demo.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.log.demo.model.User;
import br.com.log.demo.service.UserService;
import br.com.log.demo.web.dto.UserDto;

@Controller
@RequestMapping("/especiarias")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute("user")
	public UserDto userDto() {
		return new UserDto();
	}
	
	@GetMapping("/cadastraruser")
	public String showRegistrationForm() {
		
		return "/cadastros/newCadastro";
	}
	
	@PostMapping("/cadastraruser")
	public String registerUserAccount(@ModelAttribute("user") UserDto userDto) {
		// Salvar no banco
		
		userService.save(userDto);
		
		return "redirect:/especiarias/login";  // redireciona para uma "rota"
	}
	
	@ResponseBody
	@RequestMapping(value="/especiarias/cadastraruser/ajax/getEmail/{campo}/{valor}")
	public String getSearchResultViaAjaxRegister(@PathVariable("campo") String campo, 
			                                     @PathVariable("valor") String valor) {
		
		String msg = "";
		
		UserDto userDto = new UserDto();
		userDto.setEmail(valor);
		User user = userService.findByEmail(userDto.getEmail());
		if(user != null) {
			msg = "Email já existe, escolha um email válido!";
		}
		return msg;
	}

	
	@GetMapping("/vitrine")
	public String vitrine() {
		return "/visao/sempaginicial";
	}
	

}