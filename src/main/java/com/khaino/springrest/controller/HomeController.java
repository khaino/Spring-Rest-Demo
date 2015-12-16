package com.khaino.springrest.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.khaino.springrest.constant.Constant;
import com.khaino.springrest.model.HomeMessage;

@RestController
@RequestMapping(value = "/home", 
	produces = { MediaType.APPLICATION_JSON_VALUE })
public class HomeController {

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Resource<HomeMessage>> home(){
		
		final HomeMessage homeMessage = new HomeMessage();
		homeMessage.setMessage("This is a simple demo to demonstrate Spring Restful, please follow the links to explore more");
		homeMessage.setBy("Pau Sian Khai");
		Resource<HomeMessage> resource =  new Resource<HomeMessage>(homeMessage);
				
		resource.add(linkTo(TeacherController.class)
        		.withRel(Constant.TEACHER));
		
		resource.add(linkTo(SubjectController.class)
        		.withRel(Constant.SUBJECT));
        		
		return new ResponseEntity<Resource<HomeMessage>>(resource, HttpStatus.OK);
		
	}
}
