package com.in28.rest.websevices.resfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28.rest.websevices.resfulwebservices.exception.UserNotFoundException;

@RestController
public class UserResource {
	
	@Autowired
	private UserDAOService service;
	
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		
		User user = service.findOne(id);
		if(user==null) {
			throw new UserNotFoundException("id-" + id);
		}
		
		return user;		
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {		
		User saveUser = service.save(user);
		
		//created
		// /user/4
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		.buildAndExpand(saveUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		
		User user = service.deleteById(id);
		
		if(user==null) {
			throw new UserNotFoundException("id-" + id);
		}		
			
	}

}
