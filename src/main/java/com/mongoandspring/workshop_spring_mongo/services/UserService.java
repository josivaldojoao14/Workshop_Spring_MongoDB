package com.mongoandspring.workshop_spring_mongo.services;

import java.util.List;

import com.mongoandspring.workshop_spring_mongo.domain.Role;
import com.mongoandspring.workshop_spring_mongo.domain.User;

public interface UserService {
	List<User> findAll();
	User findById(String id);
	User insert(User user);
	void delete(String id);
	User update(User user);
	User findByEmail(String email);
	User findByUsername(String username);
	Role saveRole(Role role);
	void addRoleToUser(String username, String roleName);
}
