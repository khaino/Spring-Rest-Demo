package com.khaino.springrest.service;

import java.util.List;

import com.khaino.springrest.exception.NotExistException;
import com.khaino.springrest.model.Subject;

public interface SubjectService {

	public List<Subject> getAllSubjects();
	
	public Subject getSubject(int subjectId) throws NotExistException;
	
	public Subject insertSubject(Subject subject);
	
	public void updateSubject(int subjectId, Subject subject);
	
	public void deleteSubject(int subjectId);
}
