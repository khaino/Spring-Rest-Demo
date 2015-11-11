package com.khaino.springrest.controller;

import java.util.List;

import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.khaino.springrest.assembler.SubjectResourceAssembler;
import com.khaino.springrest.exception.NotExistException;
import com.khaino.springrest.model.ExceptionMessage;
import com.khaino.springrest.model.Subject;
import com.khaino.springrest.service.SubjectService;

@RestController
@RequestMapping( value = "/subjects", produces = { MediaType.APPLICATION_JSON_VALUE } )
public class SubjectController {
	
	final private SubjectResourceAssembler subjectResourceAssembler;
	final private SubjectService subjectService;
	
	@Autowired
	SubjectController(SubjectResourceAssembler subjectResourceAssembler, SubjectService subjectService){
		this.subjectResourceAssembler = subjectResourceAssembler;
		this.subjectService = subjectService;
	}
	
	@RequestMapping( method = RequestMethod.GET )
	public ResponseEntity<Resources<Resource<Subject>>> getAllSjubjects(){		
		
		List<Subject> subjectList = subjectService.getAllSubjects();
		Resources<Resource<Subject>> resource = this.subjectResourceAssembler.toSubjectResourceList(subjectList);
		return new ResponseEntity<Resources<Resource<Subject>>>(resource, HttpStatus.OK);
	}


	@RequestMapping( method = RequestMethod.GET, value = "/{subjectId}" )
	public Resource<Subject> getSubject(@PathVariable int subjectId) throws NotExistException{
		
		Subject subject = subjectService.getSubject(subjectId);
		Resource<Subject> resource = subjectResourceAssembler.toSubjectResource(subject);
		return resource;		
	}
	
	@RequestMapping( method = RequestMethod.POST )
	public ResponseEntity<Resource<Subject>> saveTeacher(@RequestBody Subject subject) throws NotExistException{
		
		Subject newSubject = subjectService.insertSubject(subject);
		Resource<Subject> resource = subjectResourceAssembler.toSubjectResource(newSubject);
		return new ResponseEntity<Resource<Subject>>(resource, HttpStatus.CREATED);		
	}
	
	@RequestMapping( method = RequestMethod.PUT, value = "/{subjectId}" )
	public ResponseEntity<Resource<Subject>> updateSubject(@RequestBody Subject subject, @PathVariable int subjectId) {
		
		subject.setSubjectId(subjectId);
		subjectService.updateSubject(subjectId, subject);	
		
		Resource<Subject> resource = subjectResourceAssembler.toSubjectResource(subject);
		return new ResponseEntity<Resource<Subject>>(resource, HttpStatus.OK);	
	}
	
	
	@RequestMapping( method = RequestMethod.DELETE, value = "/{subjectId}" )
	public ResponseEntity<Void> deleteSubject( @PathVariable int subjectId){
		
		subjectService.deleteSubject(subjectId);	
		return new ResponseEntity<Void>( HttpStatus.OK);		
	}
	
	
	@ExceptionHandler({ NotExistException.class })
    ResponseEntity<ExceptionMessage> handleNotFounds(Exception e) {
		ExceptionMessage exceptionMessage = 
				new ExceptionMessage(HttpStatus.NOT_FOUND.toString(), e.getMessage());
		return new ResponseEntity<ExceptionMessage>(exceptionMessage, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler({ PSQLException.class })
    ResponseEntity<ExceptionMessage> duplicateKey(Exception e) {
		ExceptionMessage exceptionMessage = 
				new ExceptionMessage(HttpStatus.CONFLICT.toString(), e.getMessage());
		return new ResponseEntity<ExceptionMessage>(exceptionMessage, HttpStatus.NOT_FOUND);
    }

}
