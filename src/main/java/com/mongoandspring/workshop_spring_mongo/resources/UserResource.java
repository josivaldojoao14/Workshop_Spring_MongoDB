package com.mongoandspring.workshop_spring_mongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongoandspring.workshop_spring_mongo.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@GetMapping
	public ResponseEntity<List<User>> getAll(){
		List<User> list = new ArrayList<>();
		
		User u1 = new User("1", "Josivaldo", "josi@gmail.com");
		User u2 = new User("2", "Maria", "maria@gmail.com");
		User u3 = new User("3", "Marcos", "marcos@gmail.com");
		User u4 = new User("4", "Edvaldo", "edvaldo@gmail.com");

		list.addAll(Arrays.asList(u1, u2, u3, u4));

		return ResponseEntity.ok().body(list);
	}
}
