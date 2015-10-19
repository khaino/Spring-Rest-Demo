package com.khaino.springrest.model;

public class Comment {
	
	private int commentId;
	private String commentText;
	private int commentBy;
	
	public Comment(){
		
	}	
	public Comment(int commentId, String commentText, int commentBy) {
		super();
		this.commentId = commentId;
		this.commentText = commentText;
		this.commentBy = commentBy;
	}
	
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public int getCommentBy() {
		return commentBy;
	}
	public void setCommentBy(int commentBy) {
		this.commentBy = commentBy;
	}
	
	

}
