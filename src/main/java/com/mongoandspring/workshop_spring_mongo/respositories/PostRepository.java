package com.mongoandspring.workshop_spring_mongo.respositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongoandspring.workshop_spring_mongo.domain.Post;

public interface PostRepository extends MongoRepository<Post, String> {

}
