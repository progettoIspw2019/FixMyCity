package com.ispw.fixmycity.logic.exceptions;

public class InvalidReportException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3890505571430059817L;
	
	public InvalidReportException(String errorMessage) {
		super(errorMessage);
	}
}
