package com.khaino.springrest.service;

import java.util.List;

import com.khaino.springrest.exception.NotExistException;
import com.khaino.springrest.model.Lesson;

public interface LessonService {

	public List<Lesson> getAllLessons(int subjectId);
	
	public Lesson getLesson(int lessonId) throws NotExistException;
	
	public Lesson insertLesson(Lesson lesson);
	
	public void updateLesson(int lessonId, Lesson lesson);
	
	public void deleteLesson(int lessonId);
	
}
