package com.mongoandspring.workshop_spring_mongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongoandspring.workshop_spring_mongo.domain.Role;
import com.mongoandspring.workshop_spring_mongo.domain.User;
import com.mongoandspring.workshop_spring_mongo.respositories.RoleRepository;
import com.mongoandspring.workshop_spring_mongo.respositories.UserRepository;
import com.mongoandspring.workshop_spring_mongo.services.exceptions.ObjectNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Service @Transactional @Slf4j 
public class UserServiceImpl implements UserService, UserDetailsService{
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			@Lazy PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username); 
		if(user == null) {
			log.info("User {} not found in the database", username);
			throw new UsernameNotFoundException("User not found");
		}
		log.info("User {} authenticated", username);
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), 
				user.getPassword(), true, true, true, true, 
				user.getAuthorities());
	}
	
	@Override
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	@Override
	public User findById(String id) {
		log.info("Fetching user with the id {}", id);
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}
	
	@Override
	public User saveUser(User user) {
		log.info("Saving new user {} to the database", user.getName());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.insert(user);
	}
	
	@Override
	public void delete(String id) {
		log.info("Deleting user with the id {}", id);
		findById(id);
		userRepository.deleteById(id);
	}
	
	@Override
	public User update(User user) {
		log.info("Updating user with the id {}", user.getId());
		User newObj = findById(user.getId());
		updateData(newObj, user);
		return userRepository.save(newObj);
	}
	
	@Override
	public User findByEmail(String email) {
		log.info("Fetching user with the email {}", email);
		return userRepository.findByEmail(email);
	}
	
	@Override
	public User findByUsername(String username) {
		log.info("Fetching user with the username {}", username);
		return userRepository.findByUsername(username);
	}
	
	@Override
	public Role saveRole(Role role) {
		log.info("Saving new role {} to the database", role.getRoleName());
		return roleRepository.save(role);
	}
	
	@Override
	public void addRoleToUser(String username, String roleName) {
		log.info("Adding role {} to user {}", roleName, username);
		User user = userRepository.findByUsername(username);
		Role role = roleRepository.findByName(roleName);
		user.getRoles().add(role);
		userRepository.save(user);
	}
	
	
	private void updateData(User newObj, User user) {
		newObj.setName(user.getName());
		newObj.setEmail(user.getEmail());	
	}	
}
