package com.khaino.springrest.exception;

public class NotExistException extends Exception {

	private static final long serialVersionUID = 1L;

	private static final String MESSAGE_FORMAT = "%s does not exist";

    public NotExistException(String msg) {
        super(String.format(MESSAGE_FORMAT, msg));
    }
}
