package com.connectme.atm.exception;

public class EmailDoesNotExistsException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EmailDoesNotExistsException(String msg) {
		super(msg);
	}
}
