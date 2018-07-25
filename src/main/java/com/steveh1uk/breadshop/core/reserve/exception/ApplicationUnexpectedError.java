package com.steveh1uk.breadshop.core.reserve.exception;

public class ApplicationUnexpectedError extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ApplicationUnexpectedError(String message) {
		super(message);
	}

}
