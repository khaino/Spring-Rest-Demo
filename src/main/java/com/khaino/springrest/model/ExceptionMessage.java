package com.khaino.springrest.model;

public class ExceptionMessage {
	private String error_code;
	private String message;
	
	public ExceptionMessage(String error_code, String message) {
		super();
		this.error_code = error_code;
		this.message = message;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
