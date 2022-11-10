package com.mongoandspring.workshop_spring_mongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mongoandspring.workshop_spring_mongo.domain.Post;
import com.mongoandspring.workshop_spring_mongo.domain.Role;
import com.mongoandspring.workshop_spring_mongo.domain.User;
import com.mongoandspring.workshop_spring_mongo.dto.RoleDTO;
import com.mongoandspring.workshop_spring_mongo.dto.UserDTO;
import com.mongoandspring.workshop_spring_mongo.resources.util.URL;
import com.mongoandspring.workshop_spring_mongo.services.UserService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class UserResource {
	@Autowired
	private final UserService service;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping(value="/users")
	public ResponseEntity<List<UserDTO>> findAll(){
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping(value = "/user/{id}")
	public ResponseEntity<UserDTO> findUserById(@PathVariable String id){
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "/user/save")
	public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO objDto){
		User obj = new User();
		BeanUtils.copyProperties(objDto, obj);
		obj = service.saveUser(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping(value = "/user/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> update(@RequestBody UserDTO objDto, @PathVariable String id){
		User obj = new User();
		BeanUtils.copyProperties(objDto, obj);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/user/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id){
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj.getPosts());
	}
	
	@GetMapping(value = "/user/findByEmail")
	public ResponseEntity<UserDTO> findByEmail(@RequestParam(value = "email") String email){
		email = URL.decodeParam(email);
		User obj = service.findByEmail(email);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@GetMapping(value = "/user/findByUsername")
	public ResponseEntity<UserDTO> findByUsername(@RequestParam(value = "username") String username){
		username = URL.decodeParam(username);
		User obj = service.findByUsername(username);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@PostMapping(value = "/role/save")
	public ResponseEntity<RoleDTO> saveRole(@RequestBody RoleDTO objDto){
		Role obj = new Role();
		BeanUtils.copyProperties(objDto, obj);
		obj = service.saveRole(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping(value = "/role/addToUser")
	public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
		service.addRoleToUser(form.getUsername(), form.getRoleName());
		return ResponseEntity.noContent().build();
	}	
}

@Data
class RoleToUserForm{
	private String username;
	private String roleName;
}
