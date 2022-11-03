package com.mongoandspring.workshop_spring_mongo.respositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongoandspring.workshop_spring_mongo.domain.Post;

public interface PostRepository extends MongoRepository<Post, String> {
	
	List<Post> findByTitleContainingIgnoreCase(String text);
}
