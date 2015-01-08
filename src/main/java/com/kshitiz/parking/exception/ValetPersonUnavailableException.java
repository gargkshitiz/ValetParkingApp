package com.kshitiz.parking.exception;

public class ValetPersonUnavailableException extends Exception {

	private static final long serialVersionUID = -1904825855615005479L;
	private String message;

	public ValetPersonUnavailableException(String message){
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
