package com.in28.rest.websevices.resfulwebservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@GetMapping(path ="/hello-world")
	public String helloWorld() {
		return "Hello world";
	}	

}
