package br.com.log.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SemController {

	@GetMapping("/especiarias/sobrenos")
	public String semsobrenos() {
		return "/visao/semsobrenos";
	}
	@GetMapping("/especiarias/pagProdutos")
	public String semprodutos() {
		return "/visao/sempagProdutos";
	}
	

	
}
