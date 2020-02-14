package com.ispw.fixmycity.logic.exceptions;

public class UserNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8336104453701013950L;

	public UserNotFoundException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}
