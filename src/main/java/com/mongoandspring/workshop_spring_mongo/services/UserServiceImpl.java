package com.mongoandspring.workshop_spring_mongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongoandspring.workshop_spring_mongo.domain.Role;
import com.mongoandspring.workshop_spring_mongo.domain.User;
import com.mongoandspring.workshop_spring_mongo.respositories.RoleRepository;
import com.mongoandspring.workshop_spring_mongo.respositories.UserRepository;
import com.mongoandspring.workshop_spring_mongo.services.exceptions.ObjectNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service @RequiredArgsConstructor @Transactional @Slf4j 
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	
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
	public User insert(User user) {
		log.info("Saving new user {} to the database", user.getName());
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
		log.info("Saving new role {} to the database", role.getName());
		return roleRepository.save(role);
	}
	
	@Override
	public void addRoleToUser(String username, String roleName) {
		log.info("Adding role {} to user {}", roleName, username);
		User user = userRepository.findByUsername(username);
		Role role = roleRepository.findByName(roleName);
		user.getRoles().add(role);
	}
	
	private void updateData(User newObj, User user) {
		newObj.setName(user.getName());
		newObj.setEmail(user.getEmail());	
	}
}
