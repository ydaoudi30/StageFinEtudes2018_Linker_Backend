package com.ant.linker.administration.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@CrossOrigin
public class ArticleController {
	 
	
	
	@GetMapping("/test")
	public String  helloAntLinker() {
		
		return "Hello back end Ant Linker";
	}
}
