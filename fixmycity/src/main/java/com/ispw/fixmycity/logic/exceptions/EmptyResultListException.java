package com.ispw.fixmycity.logic.exceptions;

public class EmptyResultListException extends Exception {

	private static final long serialVersionUID = -4375782164814508650L;

	public EmptyResultListException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}
