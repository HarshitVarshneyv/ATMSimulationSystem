package com.connectme.atm.exception;

public class AccountDoesNotExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AccountDoesNotExistsException(String msg) {
		super(msg);
	}
}
