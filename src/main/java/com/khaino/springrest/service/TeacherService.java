package com.khaino.springrest.service;

import java.util.List;

import com.khaino.springrest.exception.NotExistException;
import com.khaino.springrest.model.Subject;
import com.khaino.springrest.model.Teacher;

public interface TeacherService {
	
	public List<Teacher> getAllTeacher();
	
	public Teacher getTeacher(int teacherId) throws NotExistException;
	
	public Teacher insertTeacher(Teacher teacher);
	
	public void updateTeacher(int teacherId, Teacher teacher);
	
	public void deleteTeacher(int teacherId);
	
	public List<Subject> getAllSubjectsByTeacher( int teacherId );
	
}
