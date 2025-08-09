package com.barath.restgoals.controller;

import java.util.Locale;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.barath.restgoals.bean.HelloWorldBean;

@RestController
public class HelloWorldController {
	
	/*@RequestMapping(path = "/hello-world",method = RequestMethod.GET)
	public String helloWorld(){
		return "hello world";
	}*/
	MessageSource source;
	private HelloWorldController(MessageSource source) {
		this.source=source;
	}
	
	@GetMapping(path = "hello-world")
	public String helloWorld() {
		return "hello world";
	}
	@GetMapping(path = "hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("hello world");
	}
	
	@GetMapping(path="hello-world/path-parameter/{name}/{age}")
	public HelloWorldBean helloWorldPathParameter(@PathVariable String name, @PathVariable String age) {
		return new HelloWorldBean(String.format("Hello World, %s . your age is %s", name,age));
	}
	
	@GetMapping(path = "/hello-world-internationalization")
	public String helloWorldInternationlization() {
		Locale locale =  LocaleContextHolder.getLocale();
		return source.getMessage("good.morning.message", null, "default", locale);
	}
	
}
