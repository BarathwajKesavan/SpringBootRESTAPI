package com.barath.restgoals.jpa;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.barath.restgoals.user.Post;
import com.barath.restgoals.user.User;
import com.barath.restgoals.user.UserNotFoundException;

import jakarta.validation.Valid;

@RestController
public class UserJPAController {
	@Autowired
	private UserJPARepository repository;
	@Autowired
	private PostJPARepository postRepository;

	@GetMapping(path = "/users-jpa")
	public List<User> retrieveAllUsers() {
		return repository.findAll();
	}

	@GetMapping(path = "/users-jpa/{id}")
	private Optional<User> getUserByID(@PathVariable int id) {

		Optional<User> user = repository.findById(id);

		if (!user.isPresent())
			throw new UserNotFoundException(String.valueOf(id));
		return user;
	}

	@PostMapping(path = "/users-jpa")
	private ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		user = repository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "/users-jpa/{id}")
	private void deleteUser(@PathVariable int id) {
		repository.deleteById(id);
	}

	@GetMapping(path = "/users-jpa/{id}/posts")
	private List<Post> retrievePostsForUser(@PathVariable int id) {
		Optional<User> user = repository.findById(id);
		if (!user.isPresent())
			throw new UserNotFoundException(String.valueOf(id));
		 return user.get().getPost();
	}
	
	@PostMapping(path = "/users-jpa/{id}/posts")
	private ResponseEntity<User> addUser(@PathVariable int id, @Valid @RequestBody Post post) {
		Optional<User> user = repository.findById(id);
		if (!user.isPresent())
			throw new UserNotFoundException(String.valueOf(id));
		post.setUser(user.get());
		Post newPost= postRepository.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPost.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	@GetMapping(path = "/users-jpa/{userId}/posts/{postId}")
	private Optional<Post> getPostById(@PathVariable int userId, @PathVariable int postId){
		Optional<User> user = repository.findById(userId);
		if (!user.isPresent())
			throw new UserNotFoundException(String.valueOf(userId));
		return postRepository.findById(postId);		
		
	}
	
	
}
