package com.mongoandspring.workshop_spring_mongo.respositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mongoandspring.workshop_spring_mongo.domain.Role;

public interface RoleRepository extends MongoRepository<Role, String>{
	@Query("{ 'roleName' : ?0 }")
	Role findByName(String name);
}
