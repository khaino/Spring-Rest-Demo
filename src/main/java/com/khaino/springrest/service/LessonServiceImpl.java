package com.khaino.springrest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.khaino.springrest.dao.LessonDao;
import com.khaino.springrest.exception.NotExistException;
import com.khaino.springrest.model.Lesson;

public class LessonServiceImpl implements LessonService {
	
	@Autowired
	private LessonDao lessonDao;

	@Override
	public List<Lesson> getAllLessons() {
		
		return lessonDao.getAllLessons();
	}

	@Override
	public Lesson getLesson(int lessonId) throws NotExistException {
		Lesson lesson = lessonDao.getLesson(lessonId);
		if( lesson == null){
			throw new NotExistException("Lesson with id = " + lessonId);
		}
		return lesson;
	}

	@Override
	public Lesson insertLesson(Lesson lesson) {
		int row = lessonDao.insertLesson(lesson);
		if( row != 1){
			//throw exception
		}
		int newLessonId = lessonDao.getLastInsertedId();				
		Lesson newLesson = lessonDao.getLesson(newLessonId);
		return newLesson;
	}

	@Override
	public void updateLesson(int lessonId, Lesson lesson) {
		int row = lessonDao.updateLesson(lessonId, lesson);
		if( row != 1){
			//throw exception
		}

	}

	@Override
	public void deleteLesson(int lessonId) {
		int row = lessonDao.deleteLesson(lessonId);
		if( row != 1){
			//throw exception
		}

	}

}
