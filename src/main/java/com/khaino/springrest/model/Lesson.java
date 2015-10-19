package com.khaino.springrest.model;

public class Lesson {

	private int lessonId;
	private String lessonName;
	private int subjectId;
	
	public Lesson(){
		
	}
	
	public Lesson(int lessonId, String lessonName, int subjectId) {
		super();
		this.lessonId = lessonId;
		this.lessonName = lessonName;
		this.subjectId = subjectId;
	}
	
	public int getLessonId() {
		return lessonId;
	}
	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}
	public String getLessonName() {
		return lessonName;
	}
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	
	
}
