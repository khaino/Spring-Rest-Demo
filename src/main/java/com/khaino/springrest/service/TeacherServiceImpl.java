package com.khaino.springrest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.khaino.springrest.dao.TeacherDao;
import com.khaino.springrest.exception.NotExistException;
import com.khaino.springrest.model.Teacher;

public class TeacherServiceImpl implements TeacherService{
	
	@Autowired
	private TeacherDao teacherDao;

	@Override
	public List<Teacher> getAllTeacher() {
		
		List<Teacher> teacherList = teacherDao.getAllTeacher();
		return teacherList;
	}

	@Override
	public Teacher getTeacher(int teacherId) throws NotExistException {
		
		Teacher teacher = teacherDao.getTeacher(teacherId);
		if( teacher == null){
			throw new NotExistException("Teacher with id = " + teacherId);
		}
		return teacher;
	}

	@Override
	public Teacher insertTeacher(Teacher teacher) {

		int row = teacherDao.insertTeacher(teacher);
		if( row != 1){
			//throw exception
		}
		int teacherId = teacherDao.getLastInsertedId();				
		Teacher newTeacher = teacherDao.getTeacher(teacherId);
		return newTeacher;
	}

	@Override
	public void updateTeacher(int teacherId, Teacher teacher) {
		
		int row = teacherDao.updateTeacher(teacherId, teacher);
		if( row != 1){
			//throw exception
		}
	}

	@Override
	public void deleteTeacher(int teacherId) {
		
		int row = teacherDao.deleteTeacher(teacherId);
		if( row != 1){
			//throw exception
		}
	}

}
