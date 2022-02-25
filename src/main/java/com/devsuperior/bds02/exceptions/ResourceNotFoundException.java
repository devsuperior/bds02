package com.devsuperior.bds02.exceptions;


public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String messages) {
		super(messages);
	}

}
