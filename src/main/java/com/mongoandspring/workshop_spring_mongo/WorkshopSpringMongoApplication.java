package com.mongoandspring.workshop_spring_mongo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

			userService.saveUser(new User(null, "Maria Brown", "maria@gmail.com", "maria123", "maria3244"));
			userService.saveUser(new User(null, "Alex Green", "alex@gmail.com", "alex321", "alexin23"));
			userService.saveUser(new User(null, "Bob Grey", "bob@gmail.com", "bobmarley", "appetitefordestruction"));
			userService.saveUser(new User(null, "Josi", "josi@gmail.com", "josi123", "eh3423"));

			userService.addRoleToUser("josi123", "ROLE_ADMIN");
			userService.addRoleToUser("maria123", "ROLE_ADMIN");
			userService.addRoleToUser("maria123", "ROLE_USER");
			userService.addRoleToUser("alex321", "ROLE_USER");
			userService.addRoleToUser("bobmarley", "ROLE_USER");
			
			User maria = userService.findByUsername("maria123");
			User alex = userService.findByUsername("alex321");
			User bob = userService.findByUsername("bobmarley");

			Post p1 = new Post(null, sdf.parse("21/03/2018"), "PARTIU VIAGEM", "VOU VIAJAR PARA SÃO PAULO. ABRAÇOS!", new AuthorDTO(maria), new ArrayList<>());
			Post p2 = new Post(null, sdf.parse("23/03/2018"), "BOM DIA", "ACORDEI FELIZ HOJE!", new AuthorDTO(maria), new ArrayList<>());
			postRepository.saveAll(Arrays.asList(p1, p2));
		
			CommentDTO c1 = new CommentDTO("BOA VIAGEM, MANO!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
			CommentDTO c2 = new CommentDTO("APROVEITE!", sdf.parse("22/03/2018"), new AuthorDTO(bob));
			CommentDTO c3 = new CommentDTO("TENHA UM ÓTIMO DIA!", sdf.parse("23/03/2018"), new AuthorDTO(alex));
			
			p1.getComments().addAll(Arrays.asList(c1, c2));
			p2.getComments().addAll(Arrays.asList(c3));

			postRepository.saveAll(Arrays.asList(p1, p2));

			maria.getPosts().addAll(Arrays.asList(p1, p2));
			userService.update(maria);

			
		};
	}
}
