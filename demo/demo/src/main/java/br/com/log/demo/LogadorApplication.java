package br.com.log.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.log.demo.model.Role;
import br.com.log.demo.repository.RoleRepository;
import br.com.log.demo.service.UserService;

@SpringBootApplication
public class LogadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogadorApplication.class, args);
	}
	@Bean
	CommandLineRunner run(UserService userService, RoleRepository roleRepository) {
		return args -> {
			
		if(roleRepository.findAll().size()  == 0) {
			
			userService.saveRole(new Role("ROLE_USER"));
			userService.saveRole(new Role("ROLE_ADMIN"));

			
		}
			
			
		};
		
		
}
}
