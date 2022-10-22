package com.test.technique.exception;

public class InvalidNumberException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidNumberException(String errorMessage) {
		super(errorMessage);
	}

}
