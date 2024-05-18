package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservices.restfulwebservices.dao.UserDaoService;

@RestController
public class UserResource {
	@Autowired
	private UserDaoService userDaoService;

	
	// GET /users
	@GetMapping(path = "/users")
	public List<User> retrieveAllUser(){
		return userDaoService.findAll();
		
	}
	
	//GET /users/1
	@GetMapping(path = "/users/{id}")
	public User retrieveUser(@PathVariable int id){
		return userDaoService.findUser(id);
	}
	
	//Post /users
	@PostMapping("/users")
	public  ResponseEntity<User> createUser(@RequestBody User user) {
		User savedUser = userDaoService.saveUser(user);
		
		// /users/4 => /users, user.getId
		URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}