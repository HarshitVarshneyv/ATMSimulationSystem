package com.connectme.atm.exception;

public class PinDoesNotMatchException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public PinDoesNotMatchException(String msg) {
		super(msg);
	}
}
