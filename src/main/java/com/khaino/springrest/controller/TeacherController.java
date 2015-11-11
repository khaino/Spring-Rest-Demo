package com.khaino.springrest.controller;

import java.util.List;

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

import com.khaino.springrest.assembler.TeacherResourceAssembler;
import com.khaino.springrest.exception.NotExistException;
import com.khaino.springrest.model.ExceptionMessage;
import com.khaino.springrest.model.Subject;
import com.khaino.springrest.model.Teacher;
import com.khaino.springrest.service.TeacherService;

@RestController
@RequestMapping( value = "/teachers", produces = { MediaType.APPLICATION_JSON_VALUE } )
public class TeacherController {
	
	final private TeacherResourceAssembler teacherResourceAssembler;
	final private TeacherService teacherService;
	
	@Autowired
	TeacherController(TeacherResourceAssembler teacherResourceAssembler, TeacherService teacherService){
		this.teacherResourceAssembler = teacherResourceAssembler;
		this.teacherService = teacherService;
	}
	
	@RequestMapping( method = RequestMethod.GET )
	public ResponseEntity<Resources<Resource<Teacher>>> getAllTeacher(){		
		
		List<Teacher> teacherList = teacherService.getAllTeacher();
		Resources<Resource<Teacher>> resource = this.teacherResourceAssembler.toTeacherResourceList(teacherList);
		return new ResponseEntity<Resources<Resource<Teacher>>>(resource, HttpStatus.OK);
	}


	@RequestMapping( value = "/{teacherId}", method = RequestMethod.GET )
	public Resource<Teacher> getTeacher(@PathVariable int teacherId) throws NotExistException{
		
		Teacher teacher = teacherService.getTeacher(teacherId);
		Resource<Teacher> resource = teacherResourceAssembler.toTeacherResource(teacher);
		return resource;		
	}
	
	@RequestMapping( method = RequestMethod.POST )
	public ResponseEntity<Resource<Teacher>> saveTeacher(@RequestBody Teacher teacher) throws NotExistException{
		
		Teacher newTeacher = teacherService.insertTeacher(teacher);
		Resource<Teacher> resource = teacherResourceAssembler.toTeacherResource(newTeacher);
		return new ResponseEntity<Resource<Teacher>>(resource, HttpStatus.CREATED);		
	}
	
	@RequestMapping( value = "/{teacherId}", method = RequestMethod.PUT )
	public ResponseEntity<Resource<Teacher>> updateTeacher(@RequestBody Teacher teacher, @PathVariable int teacherId) {
		
		teacherService.updateTeacher(teacherId, teacher);	
		teacher.setTeacherId(teacherId);
		Resource<Teacher> resource = teacherResourceAssembler.toTeacherResource(teacher);
		return new ResponseEntity<Resource<Teacher>>(resource, HttpStatus.OK);	
	}
	
	
	@RequestMapping( value = "/{teacherId}", method = RequestMethod.DELETE )
	public ResponseEntity<Void> deleteTeacher( @PathVariable int teacherId){
		
		teacherService.deleteTeacher(teacherId);	
		return new ResponseEntity<Void>( HttpStatus.OK);		
	}
	
	@RequestMapping( value = "/{teacherId}/subjects",method = RequestMethod.GET )
	public ResponseEntity<Resources<Resource<Subject>>> getAllSjubjects(@PathVariable int teacherId){		
		
		List<Subject> subjectList = teacherService.getAllSubjectsByTeacher(teacherId);
		Resources<Resource<Subject>> resource = this.teacherResourceAssembler.toSubjectResourceList(subjectList, teacherId);
		return new ResponseEntity<Resources<Resource<Subject>>>(resource, HttpStatus.OK);
	}
	
	
	@ExceptionHandler({ NotExistException.class })
    ResponseEntity<ExceptionMessage> handleNotFounds(Exception e) {
		ExceptionMessage exceptionMessage = 
				new ExceptionMessage(HttpStatus.NOT_FOUND.toString(), e.getMessage());
		return new ResponseEntity<ExceptionMessage>(exceptionMessage, HttpStatus.NOT_FOUND);
    }

}
