package com.ispw.fixmycity.logic.app.exceptions;

public class UserNotFoundException extends Exception {
	public UserNotFoundException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}
