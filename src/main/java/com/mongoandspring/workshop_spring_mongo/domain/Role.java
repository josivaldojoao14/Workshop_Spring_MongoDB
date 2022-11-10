package com.mongoandspring.workshop_spring_mongo.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mongoandspring.workshop_spring_mongo.enums.RoleName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data 
@NoArgsConstructor @AllArgsConstructor
public class Role implements GrantedAuthority, Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private RoleName roleName;
	
	@Override
	@JsonIgnore
	public String getAuthority() {
		return this.roleName.toString();
	}
}
