package com.khaino.springrest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.khaino.springrest.model.Lesson;

public interface LessonDao {
	
	@Select("SELECT"
				+ " lesson_id AS lessonId,"
				+ " lesson_name AS lessonName,"
				+ " subject_id AS subjectId" 
			+ " FROM"
				+ " lesson")
	public List<Lesson> getAllLessons();


	@Select("SELECT"
				+ " lesson_id AS lessonId,"
				+ " lesson_name AS lessonName,"
				+ " subject_id AS subjectId" 
			+ " FROM"
				+ " lesson"
			+ " WHERE"
				+ " lesson_id = #{lessonId}")
	public Lesson getLesson(int lessonId);
	
	
	@Insert("INSERT INTO lesson( lesson_name, subject_id ) " +
	        "VALUES ( #{lessonName}, #{subjectId} )" )	
	public int insertLesson(Lesson lesson);

	 
	@Update("UPDATE lesson" 
			   + " SET"
			   		+ " lesson_name= #{lesson.lessonName},"
			   		+ " subject_id= #{lesson.subjectId}"
			   	+ " WHERE lesson_id= #{id}")
	public int updateLesson(@Param("id") int id, @Param("lesson")Lesson lesson);


	@Delete("DELETE FROM lesson WHERE lesson_id =#{id}")
	public int deleteLesson( int id);
	

	@Select("SELECT CURRVAL('lesson_lesson_id_seq')")
	public int getLastInsertedId();

}
