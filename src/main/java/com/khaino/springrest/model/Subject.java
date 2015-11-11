package com.khaino.springrest.model;

public class Subject {

	private int subjectId;
	private String subjectName;
	private Integer teacherId;
	
	public Subject(){
		
	}
	
	public Subject(Integer subjectId, String subjectName, int teacherId) {
		super();
		this.subjectId = subjectId;
		this.subjectName = subjectName;
		this.teacherId = teacherId;
	}
	
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", subjectName=" + subjectName + ", teacherId=" + teacherId + "]";
	}
			
}
