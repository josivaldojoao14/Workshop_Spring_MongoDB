package com.mongoandspring.workshop_spring_mongo.respositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mongoandspring.workshop_spring_mongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
	@Query("{ 'email' : ?0 }")
	User findByEmail(String email);
	
	@Query("{ 'username' : ?0 }")
	User findByUsername(String username);
}
