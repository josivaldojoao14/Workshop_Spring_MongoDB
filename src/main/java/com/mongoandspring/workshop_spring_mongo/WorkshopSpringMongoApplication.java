package com.mongoandspring.workshop_spring_mongo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mongoandspring.workshop_spring_mongo.domain.Post;
import com.mongoandspring.workshop_spring_mongo.domain.Role;
import com.mongoandspring.workshop_spring_mongo.domain.User;
import com.mongoandspring.workshop_spring_mongo.dto.AuthorDTO;
import com.mongoandspring.workshop_spring_mongo.dto.CommentDTO;
import com.mongoandspring.workshop_spring_mongo.enums.RoleName;
import com.mongoandspring.workshop_spring_mongo.respositories.PostRepository;
import com.mongoandspring.workshop_spring_mongo.respositories.RoleRepository;
import com.mongoandspring.workshop_spring_mongo.respositories.UserRepository;
import com.mongoandspring.workshop_spring_mongo.services.UserService;

@SpringBootApplication
public class WorkshopSpringMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkshopSpringMongoApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("senha123"));
	}
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PostRepository postRepository;
	
	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
			
			roleRepository.deleteAll();
			userRepository.deleteAll();
			postRepository.deleteAll();	
			
			userService.saveRole(new Role(null, RoleName.ROLE_ADMIN));
			userService.saveRole(new Role(null, RoleName.ROLE_USER));

			userService.saveUser(new User(null, "Maria Brown", "maria@gmail.com", "maria123", "maria3244", new ArrayList<>(), new ArrayList<>()));
			userService.saveUser(new User(null, "Alex Green", "alex@gmail.com", "alex321", "alexin23", new ArrayList<>(), new ArrayList<>()));
			userService.saveUser(new User(null, "Bob Grey", "bob@gmail.com", "bobmarley", "appetitefordestruction", new ArrayList<>(), new ArrayList<>()));
			userService.saveUser(new User(null, "Josi", "josi@gmail.com", "josi123", "eh3423", new ArrayList<>(), new ArrayList<>()));

			userService.addRoleToUser("josi123", "ROLE_ADMIN");
			userService.addRoleToUser("maria123", "ROLE_ADMIN");
			userService.addRoleToUser("maria123", "ROLE_USER");
			userService.addRoleToUser("alex321", "ROLE_USER");
			userService.addRoleToUser("bobmarley", "ROLE_USER");
			
			Post p1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(userService.findByUsername("maria123")));
			Post p2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(userService.findByUsername("maria123")));

			CommentDTO c1 = new CommentDTO("Boa viagem, mano!", sdf.parse("21/03/2018"), new AuthorDTO(userService.findByUsername("alex321")));
			CommentDTO c2 = new CommentDTO("Aproveite!", sdf.parse("22/03/2018"), new AuthorDTO(userService.findByUsername("bobmarley")));
			CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2018"), new AuthorDTO(userService.findByUsername("alex321")));
			
			p1.getComments().addAll(Arrays.asList(c1, c2));
			p2.getComments().addAll(Arrays.asList(c3));
			
			List<Post> list1 = new ArrayList<>();
			list1.add(p1);
			
			List<Post> list2 = new ArrayList<>();
			list1.add(p2);
			
		};
	}
}
