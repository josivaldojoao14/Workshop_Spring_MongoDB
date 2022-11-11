package com.mongoandspring.workshop_spring_mongo.dto;

import java.io.Serializable;
import java.util.Date;

import com.mongoandspring.workshop_spring_mongo.domain.Post;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
public class PostDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private Date date;
	
	public PostDTO(Post obj) {
		id = obj.getId();
		date = obj.getDate();
	}
}
