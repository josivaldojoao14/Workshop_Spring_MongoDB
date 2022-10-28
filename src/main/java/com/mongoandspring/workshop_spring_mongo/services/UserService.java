package com.mongoandspring.workshop_spring_mongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongoandspring.workshop_spring_mongo.domain.User;
import com.mongoandspring.workshop_spring_mongo.respositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
}
