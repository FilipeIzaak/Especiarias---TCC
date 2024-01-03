package br.com.log.demo.service;


import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.com.log.demo.model.Role;
import br.com.log.demo.model.User;
import br.com.log.demo.web.dto.UserDto;


@Service
public interface UserService extends UserDetailsService {
	User save(UserDto userDto);
	User findByEmail(String email);
	User update(UserDto userDto);
	void addRoleToUser(String username, String roleName);
	Role saveRole(Role role);
	User getAuthenticatedUser();
	List<User> findAllUsersByExceptPrincipalRole(String principalRole);
	User saveUser(User user);
	List<Role> findAllRoles();
	User findUserById(Long id);
}

