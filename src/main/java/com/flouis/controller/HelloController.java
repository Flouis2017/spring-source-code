package com.flouis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

	@GetMapping("/{name}/{fname}")
	public String hello(@PathVariable String name, @PathVariable String fname){
		return "hello " + name + "." + fname;
	}

}
