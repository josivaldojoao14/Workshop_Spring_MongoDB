//package com.mongoandspring.workshop_spring_mongo.config;
//
//import java.text.SimpleDateFormat;
//import java.util.Arrays;
//import java.util.TimeZone;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Configuration;
//
//import com.mongoandspring.workshop_spring_mongo.domain.Post;
//import com.mongoandspring.workshop_spring_mongo.domain.User;
//import com.mongoandspring.workshop_spring_mongo.dto.AuthorDTO;
//import com.mongoandspring.workshop_spring_mongo.dto.CommentDTO;
//import com.mongoandspring.workshop_spring_mongo.respositories.PostRepository;
//import com.mongoandspring.workshop_spring_mongo.respositories.UserRepository;
//
//@Configuration
//public class Instantiation implements CommandLineRunner {
//
//	@Autowired
//	private UserRepository userRepository;
//
//	@Autowired
//	private PostRepository postRepository;
//	
//	@Override
//	public void run(String... args) throws Exception {
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
//		
//		userRepository.deleteAll();
//		postRepository.deleteAll();	
//				
//		User maria = new User(null, "Maria Brown", "maria@gmail.com", "maria123", "123456");
//		User alex = new User(null, "Alex Green", "alex@gmail.com", "alex321", "678900");
//		User bob = new User(null, "Bob Grey", "bob@gmail.com", "bobmarley", "654321");
//		
//		userRepository.saveAll(Arrays.asList(maria, alex, bob));
//
//		Post p1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
//		Post p2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
//
//		
//		CommentDTO c1 = new CommentDTO("Boa viagem, mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
//		CommentDTO c2 = new CommentDTO("Aproveite!", sdf.parse("22/03/2018"), new AuthorDTO(bob));
//		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2018"), new AuthorDTO(alex));
//		
//		p1.getComments().addAll(Arrays.asList(c1, c2));
//		p2.getComments().addAll(Arrays.asList(c3));
//		
//		postRepository.saveAll(Arrays.asList(p1, p2));
//		
//		maria.getPosts().addAll(Arrays.asList(p1, p2));
//		userRepository.save(maria);
//	}
//
//}
