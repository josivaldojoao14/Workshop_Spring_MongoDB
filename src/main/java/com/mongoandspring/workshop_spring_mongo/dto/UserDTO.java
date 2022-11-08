package com.mongoandspring.workshop_spring_mongo.dto;

import java.io.Serializable;

import com.mongoandspring.workshop_spring_mongo.domain.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String email;
	private String username;
	private String password;
	
	public UserDTO(User obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
		username = obj.getUsername();
		password = obj.getPassword();
	}
}
