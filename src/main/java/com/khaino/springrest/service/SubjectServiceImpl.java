package com.khaino.springrest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.khaino.springrest.dao.SubjectDao;
import com.khaino.springrest.exception.NotExistException;
import com.khaino.springrest.model.Subject;

public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectDao subjectDao;
	
	@Override
	public List<Subject> getAllSubjects() {
		
		return subjectDao.getAllSubjects();
	}

	@Override
	public Subject getSubject(int subjectId) throws NotExistException {
		
		Subject subject = subjectDao.getSubject(subjectId);
		if( subject == null){
			throw new NotExistException("Subject with id = " + subjectId);
		}
		return subject;
	}

	@Override
	public Subject insertSubject(Subject subject) {
		int row = subjectDao.insertSubject(subject);
		if( row != 1){
			//throw exception
		}
		int newSubjectId = subjectDao.getLastInsertedId();				
		Subject newSubject = subjectDao.getSubject(newSubjectId);
		return newSubject;
	}

	@Override
	public void updateSubject(int subjectId, Subject subject) {
		int row = subjectDao.updateSubject(subjectId, subject);
		if( row != 1){
			//throw exception
		}
	}

	@Override
	public void deleteSubject(int subjectId) {
		int row = subjectDao.deleteSubject(subjectId);
		if( row != 1){
			//throw exception
		}
		
	}

	

}
