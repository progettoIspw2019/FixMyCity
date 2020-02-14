package com.ispw.fixmycity.logic.app.exceptions;

public class InvalidFieldException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8846482342893222034L;

	public InvalidFieldException(String errorMessage) {
		super(errorMessage);
	}
}
