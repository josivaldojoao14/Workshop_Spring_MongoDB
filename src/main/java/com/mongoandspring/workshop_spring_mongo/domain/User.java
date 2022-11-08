package com.mongoandspring.workshop_spring_mongo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "user") 
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String name;
	private String email;
	private String username;
	private String password;
	
	private final Collection<Role> roles = new ArrayList<>();
	
	@DBRef(lazy = true)
	private final List<Post> posts = new ArrayList<>();
}
