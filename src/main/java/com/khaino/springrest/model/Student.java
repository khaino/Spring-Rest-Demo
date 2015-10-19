package com.khaino.springrest.model;

public class Student {
	
	private int studentId;
	private String subjectName;
	
	public Student(){
		
	}
	public Student(int studentId, String subjectName) {
		super();
		this.studentId = studentId;
		this.subjectName = subjectName;
	}
	
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	
}
