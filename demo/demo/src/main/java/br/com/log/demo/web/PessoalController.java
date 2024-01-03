package br.com.log.demo.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.log.demo.model.User;
import br.com.log.demo.repository.UserRepository;

@Controller
public class PessoalController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/especiarias/users/infouser")
	public String logoutt() {
		return "/visao/informacao";
	}

}