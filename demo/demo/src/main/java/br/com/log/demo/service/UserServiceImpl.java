package br.com.log.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.log.demo.model.Endereco;
import br.com.log.demo.model.Role;
import br.com.log.demo.model.User;
import br.com.log.demo.repository.EnderecoRepository;
import br.com.log.demo.repository.RoleRepository;
import br.com.log.demo.repository.UserRepository;
import br.com.log.demo.web.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Override
	public User findByEmail(String email) {
		
		return userRepository.findByEmail(email);
	}

	@Override
	public User save(UserDto userDto) {
		
		// Algoritmo para criptografar a senha :  passwordEncoder
		
		User user = new User(userDto.getNome(),
				             userDto.getEmail(), 
				             passwordEncoder.encode(userDto.getPassword()),
				             new ArrayList<>());
		
		List<Endereco> enderecos = new ArrayList<>();
		Endereco endereco = new Endereco();
		// Relacionamento entre endereço e user
		endereco.setUser(user);
		enderecos.add(endereco);
		// Relacionamento entre user e endereços (array)
		user.setEnderecos(enderecos);
		
		
		      
		      userRepository.save(user);
		      user.setPrincipalRole("ROLE_USER");
		      this.addRoleToUser(user.getEmail(), "ROLE_USER");
		      return user;
		      
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		User user = userRepository.findByEmail(username);
		 
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password");
			
		}
		
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(),
				                                                  user.getPassword(),
				                                                  mapRoleToAuthorities(user.getRoles()));
	}
	
	// Método responsável em trazer os papéis relacionados ao usuário
	
	private Collection<? extends GrantedAuthority> mapRoleToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
	

	@Override
	public void addRoleToUser(String username, String roleName) {
		User user = userRepository.findByEmail(username);
		Role role = roleRepository.findByName(roleName);
		user.getRoles().add(role);
		userRepository.save(user);
		
	}

	@Override
	public Role saveRole(Role role) {
			return roleRepository.save(role);
	}

	// Método responsável em buscar o usuário autenticado
	@Override
	public User getAuthenticatedUser() {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if(principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		 }else {
			 username = principal.toString();
		 }
		  User user = userRepository.findByEmail(username);
		
		return user;
	}

	@Override
	@Transactional
	public User update(UserDto userDto) {
		
		
		User user = userRepository.findByEmail(userDto.getEmail());
		
		// Buscar o endereço Principal
		Endereco enderecoBb = user.getEnderecos().get(0);
		
		// cep, logradouro, bairro, cidade, uf
		
		enderecoBb.setCep(userDto.getEnderecos().get(0).getCep());
		enderecoBb.setLogradouro(userDto.getEnderecos().get(0).getLogradouro());
		enderecoBb.setBairro(userDto.getEnderecos().get(0).getBairro());
		enderecoBb.setCidade(userDto.getEnderecos().get(0).getCidade());
		enderecoBb.setUf(userDto.getEnderecos().get(0).getUf());
		
		// Outros dados do usuário
		
		user.setNome(userDto.getNome());
		user.setTelefone(userDto.getTelefone());
		user.setDataNascimento(userDto.getDataNascimento());
		
		return userRepository.save(user);
	}

	@Override
	public List<User> findAllUsersByExceptPrincipalRole(String principalRole) {
		
		return userRepository.findAllUsersByExceptPrincipalRole(principalRole);
	}

	@Override
	public User saveUser(User user) {
		
		return userRepository.save(user);
	}

	@Override
	public List<Role> findAllRoles() {

		return roleRepository.findAll();
	}

	@Override
	public User findUserById(Long id) {

		return userRepository.findById(id).get();
	}

}