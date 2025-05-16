package com.connectme.atm.exception;

public class PasswordDoesNotMatchException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public PasswordDoesNotMatchException(String msg) {
		super(msg);
	}
}
