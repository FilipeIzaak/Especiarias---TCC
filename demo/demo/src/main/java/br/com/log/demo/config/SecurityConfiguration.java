package br.com.log.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;


import br.com.log.demo.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
	    http
        .authorizeRequests()
            .antMatchers("/css/**", "/js/**", "/img/**", "/registration", "/especiarias/cadastraruser", "/especiarias/cadastrarendereco", "/especiarias/vitrine", "/especiarias/finalizapedido","/especiarias/pagProdutos","/especiarias/sobrenos").permitAll()
            .antMatchers(HttpMethod.GET, "/especiarias/users/**").hasAnyAuthority("ROLE_USER")
            .antMatchers(HttpMethod.POST, "/especiarias/users/**").hasAnyAuthority("ROLE_USER")
            .antMatchers("/especiarias/gerenciamento/**").hasAnyAuthority("ROLE_ADMIN")
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/especiarias/login")
            .defaultSuccessUrl("/especiarias/living-room", true)
            .passwordParameter("password")
            .usernameParameter("username")
            .permitAll()
            .and()
        .logout()
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .logoutSuccessUrl("/login?logout")
            .permitAll()
            .and()
        .httpBasic()
            .and()
        .csrf().disable(); // Desabilitar CSRF para simplificar; considere ativar em um ambiente de produção
}
	
}