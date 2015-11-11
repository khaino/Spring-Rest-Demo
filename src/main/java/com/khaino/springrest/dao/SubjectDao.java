package com.khaino.springrest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.khaino.springrest.model.Subject;

public interface SubjectDao {
	
	@Select("SELECT"
				+ " subject_id AS subjectId,"
				+ " subject_name AS subjectName,"
				+ " teacher_id AS teacherId" 
			+ " FROM"
				+ " subject")
	public List<Subject> getAllSubjects();
	
	
	@Select("SELECT"
				+ " subject_id AS subjectId,"
				+ " subject_name AS subjectName,"
				+ " teacher_id AS teacherId" 
			+ " FROM"
				+ " subject"
			+ " WHERE"
				+ " subject_id = #{subjectid}")
	public Subject getSubject(int subjectId);
	
	
	@Insert("INSERT INTO subject( subject_name ) " +
            "VALUES ( #{subjectName} )" )	
	public int insertSubject(Subject subject);
	
	
	@Update("UPDATE subject" 
			   + " SET"
			   		+ " subject_name= #{subject.subjectName},"
			   		+ " teacher_id= #{subject.teacherId}"
			   	+ " WHERE subject_id= #{id}")
	public int updateSubject(@Param("id") int id, @Param("subject")Subject subject);
	

	@Delete("DELETE FROM subject WHERE subject_id =#{id}")
	public int deleteSubject( int id);
	
	
	@Update("UPDATE subject" 
			   + " SET"
			   		+ " teacher_id= #{subject.teacherId}"
			   	+ " WHERE subject_id= #{id}")
	public int assignTeacher(@Param("id") int id, @Param("subject")Subject subject);
	
	
	@Select("SELECT CURRVAL('subject_subject_id_seq')")
	public int getLastInsertedId();
	 
}
