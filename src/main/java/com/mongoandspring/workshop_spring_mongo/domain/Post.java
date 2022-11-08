package com.mongoandspring.workshop_spring_mongo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongoandspring.workshop_spring_mongo.dto.AuthorDTO;
import com.mongoandspring.workshop_spring_mongo.dto.CommentDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Post implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private Date date;
	private String title;
	private String body;
	private AuthorDTO author;
	
	private final List<CommentDTO> comments = new ArrayList<>();
	
	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}
}
