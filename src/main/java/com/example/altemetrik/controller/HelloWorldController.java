package com.example.altemetrik.controller;

import java.util.HashMap;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {
	
	@RequestMapping({"/hello"})
	public HashMap<String, String> firstPage() {
		HashMap<String, String> map = new HashMap<>();
		UserDetails userDetails =  (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    map.put("Message", "Welcome!");
	    map.put("Authenticated-User", userDetails.getUsername());
	    return map;

	}

}
