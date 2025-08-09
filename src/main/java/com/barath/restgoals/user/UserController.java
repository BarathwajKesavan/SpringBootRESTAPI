package com.barath.restgoals.user;

import java.net.URI;
import java.util.ArrayList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
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

import jakarta.validation.Valid;

@RestController
public class UserController {
	@Autowired
	private UserDAO dao;
	
	@GetMapping(path = "/users")
	public ArrayList<User> retrieveAllUsers(){
		return dao.findAllUsers();
	}
	@GetMapping(path="/users/{id}")
	private EntityModel<User> getUserByID(@PathVariable int id) {
		
		EntityModel<User> model = EntityModel.of(dao.findUserByID(id));
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		model.add(link.withRel("all_users"));
		if( dao.findUserByID(id)==null )throw new UserNotFoundException(String.valueOf(id));
		return model;
	}
	@PostMapping(path="/users")
	private ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		user =  dao.addUser(user);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	@DeleteMapping(path="/users/{id}")
	private void deleteUser(@PathVariable int id) {
		dao.deleteUser(id);
	}
}
