//package com.mongoandspring.workshop_spring_mongo.config;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.TimeZone;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Configuration;
//
//import com.mongoandspring.workshop_spring_mongo.domain.Post;
//import com.mongoandspring.workshop_spring_mongo.domain.Role;
//import com.mongoandspring.workshop_spring_mongo.domain.User;
//import com.mongoandspring.workshop_spring_mongo.dto.AuthorDTO;
//import com.mongoandspring.workshop_spring_mongo.dto.CommentDTO;
//import com.mongoandspring.workshop_spring_mongo.respositories.PostRepository;
//import com.mongoandspring.workshop_spring_mongo.respositories.RoleRepository;
//import com.mongoandspring.workshop_spring_mongo.respositories.UserRepository;
//import com.mongoandspring.workshop_spring_mongo.services.UserService;
//
//@Configuration
//public class Instantiation implements CommandLineRunner {
//
//	@Autowired
//	private UserRepository userRepository;
//	
//	@Autowired
//	private RoleRepository roleRepository;
//
//	@Autowired
//	private PostRepository postRepository;
//	
//	@Autowired
//	private UserService userService;
//	
//	@Override
//	public void run(String... args) throws Exception {
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
//		
//		roleRepository.deleteAll();
//		userRepository.deleteAll();
//		postRepository.deleteAll();	
//		
//		Role r1 = roleRepository.save(new Role(null, "ROLE_USER"));
//		Role r2 = roleRepository.save(new Role(null, "ROLE_MANAGER"));
//		Role r3 = roleRepository.save(new Role(null, "ROLE_ADMIN"));
//		Role r4 = roleRepository.save(new Role(null, "ROLE_SUPER_ADMIN"));
//		
//		Collection<Role> roleMaria = new ArrayList<>(Arrays.asList(r1, r2));
//		Collection<Role> roleAlex = new ArrayList<>(Arrays.asList(r2, r4));
//		Collection<Role> roleBob = new ArrayList<>(Arrays.asList(r1, r3));
//		Collection<Role> roleJosi = new ArrayList<>(Arrays.asList(r1));
//		
//		userRepository.save(new User(null, "Maria Brown", "maria@gmail.com", "maria123", "123456", roleMaria, null));
//		userRepository.save(new User(null, "Alex Green", "alex@gmail.com", "alex321", "678900", roleAlex, null));
//		userRepository.save(new User(null, "Bob Grey", "bob@gmail.com", "bobmarley", "654321", roleBob, null));
//		userRepository.save(new User(null, "Josi", "josi@gmail.com", "josi123", "842352", roleJosi, null));
//		
//		Post p1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(userService.findByUsername("maria123")));
//		Post p2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(userService.findByUsername("maria123")));
//
//		CommentDTO c1 = new CommentDTO("Boa viagem, mano!", sdf.parse("21/03/2018"), new AuthorDTO(userService.findByUsername("alex321")));
//		CommentDTO c2 = new CommentDTO("Aproveite!", sdf.parse("22/03/2018"), new AuthorDTO(userService.findByUsername("bobmarley")));
//		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2018"), new AuthorDTO(userService.findByUsername("alex321")));
//		
//		p1.getComments().addAll(Arrays.asList(c1, c2));
//		p2.getComments().addAll(Arrays.asList(c3));
//		
//		postRepository.saveAll(Arrays.asList(p1, p2));
//		
//		User maria = userService.findByUsername("maria123");
//		maria.getPosts().addAll(Arrays.asList(p1, p2));
//		userRepository.save(maria);
//	}
//
//}
