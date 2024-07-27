package com.example.spring_security_jsp_user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public boolean userExists(String username) {
		if(userRepository.findUserByUsername(username) == null) {
			return false;
			
		} else {
			return true;
		}
	}
	
	public User createNewUser(User user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		user.addRoles(roleRepository.findById(1).get());
		return userRepository.save(user);
	}

}
