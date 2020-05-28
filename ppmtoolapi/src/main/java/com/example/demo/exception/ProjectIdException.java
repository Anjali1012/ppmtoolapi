package com.example.demo.exception;

public class ProjectIdException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProjectIdException(String errMsg) {
		super(errMsg);
	}
}
