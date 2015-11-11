package com.khaino.springrest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.khaino.springrest.model.Subject;
import com.khaino.springrest.model.Teacher;

public interface TeacherDao {

	@Select("SELECT teacher_id AS teacherId, teacher_name AS teacherName " +
	  "FROM teacher")
	public List<Teacher> getAllTeacher();
	
	
	@Select("SELECT teacher_id AS teacherId, teacher_name AS teacherName " +
			  "FROM teacher " +
			"WHERE teacher_id = #{teacherId}")
	public Teacher getTeacher(int teacherId);
	
	
	@Insert("INSERT INTO teacher(  teacher_name ) " +
            "VALUES ( #{teacherName} )" )	
	public int insertTeacher(Teacher teacher);
	
	
	@Update("UPDATE teacher " + 
			   "SET teacher_name= #{teacher.teacherName} " +
						 "WHERE teacher_id= #{id}")
	public int updateTeacher(@Param("id") int id, @Param("teacher")Teacher teacher);
	

	@Delete("DELETE FROM teacher WHERE teacher_id =#{id}")
	public int deleteTeacher( int id);
	
	
	@Select("SELECT CURRVAL('teacher_teacher_id_seq')")
	public int getLastInsertedId();
	
	
	@Select("SELECT"
			+ " subject_id AS subjectId,"
			+ " subject_name AS subjectName,"
			+ " teacher_id AS teacherId" 
		+ " FROM"
			+ " subject"
		+ " WHERE"
			+ " teacher_id =#{teacherId}")
	public List<Subject> getAllSubjectsByTeacher( int teacherId );
	
}
