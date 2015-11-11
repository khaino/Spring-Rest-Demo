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

import com.khaino.springrest.assembler.LessonResourceAssembler;
import com.khaino.springrest.exception.NotExistException;
import com.khaino.springrest.model.ExceptionMessage;
import com.khaino.springrest.model.Lesson;
import com.khaino.springrest.service.LessonService;

@RestController
@RequestMapping(value = "/subjects/{subjectId}/lessons", produces = { MediaType.APPLICATION_JSON_VALUE })
public class LessonController {

	final private LessonResourceAssembler lessonResourceAssembler;
	final private LessonService lessonService;

	@Autowired
	public LessonController(LessonResourceAssembler lessonResourceAssembler, LessonService lessontService) {
		super();
		this.lessonResourceAssembler = lessonResourceAssembler;
		this.lessonService = lessontService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Resources<Resource<Lesson>>> getAllSjubject(@PathVariable int subjectId) {

		List<Lesson> lessonList = lessonService.getAllLessons(subjectId);
		Resources<Resource<Lesson>> resource = this.lessonResourceAssembler.toLessonResourceList(lessonList, subjectId);
		return new ResponseEntity<Resources<Resource<Lesson>>>(resource, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{lessonId}")
	public Resource<Lesson> getLesson(@PathVariable int lessonId) throws NotExistException {

		Lesson lesson = lessonService.getLesson(lessonId);
		Resource<Lesson> resource = lessonResourceAssembler.toLessonResource(lesson);
		return resource;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Resource<Lesson>> saveTeacher(@RequestBody Lesson lesson) throws NotExistException {

		Lesson newLesson = lessonService.insertLesson(lesson);
		Resource<Lesson> resource = lessonResourceAssembler.toLessonResource(newLesson);
		return new ResponseEntity<Resource<Lesson>>(resource, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{lessonId}")
	public ResponseEntity<Resource<Lesson>> updateLesson(@RequestBody Lesson lesson, @PathVariable int lessonId) {

		lesson.setLessonId(lessonId);
		lessonService.updateLesson(lessonId, lesson);

		Resource<Lesson> resource = lessonResourceAssembler.toLessonResource(lesson);
		return new ResponseEntity<Resource<Lesson>>(resource, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{lessonId}")
	public ResponseEntity<Void> deleteLesson(@PathVariable int lessonId) {

		lessonService.deleteLesson(lessonId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@ExceptionHandler({ NotExistException.class })
	ResponseEntity<ExceptionMessage> handleNotFounds(Exception e) {
		ExceptionMessage exceptionMessage = new ExceptionMessage(HttpStatus.NOT_FOUND.toString(), e.getMessage());
		return new ResponseEntity<ExceptionMessage>(exceptionMessage, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ PSQLException.class })
	ResponseEntity<ExceptionMessage> duplicateKey(Exception e) {
		ExceptionMessage exceptionMessage = new ExceptionMessage(HttpStatus.CONFLICT.toString(), e.getMessage());
		return new ResponseEntity<ExceptionMessage>(exceptionMessage, HttpStatus.NOT_FOUND);
	}
}
