package com.mongoandspring.workshop_spring_mongo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.mongoandspring.workshop_spring_mongo.domain.Role;
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
	
	private Collection<Role> roles = new ArrayList<>();
	private List<PostDTO> posts = new ArrayList<>();
	
	public UserDTO(User obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
		username = obj.getUsername();
		password = obj.getPassword();
		roles = obj.getRoles();
		posts = obj.getPosts().stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
	}
	
	
}
