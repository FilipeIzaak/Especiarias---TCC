package br.com.log.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeralController {
	

	
	@GetMapping("/especiarias/users/sobrenos")
	public String sobrenos() {
		return "/visao/sobrenos";
	}
	
	@GetMapping("/especiarias/gerenciamento")
	public String gerente() {
		return "/gerenciamento/lobby";
	}
	
	
	@GetMapping("/login?logout")
	public String sair() {
		return "/visao/sair";
	}
}