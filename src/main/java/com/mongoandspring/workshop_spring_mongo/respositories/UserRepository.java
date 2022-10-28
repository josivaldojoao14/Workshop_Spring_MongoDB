package com.mongoandspring.workshop_spring_mongo.respositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongoandspring.workshop_spring_mongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
