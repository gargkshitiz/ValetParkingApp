package com.kshitiz.parking.exception;

public class IllegalValetTokenException extends Exception {

	private static final long serialVersionUID = -30142175074381239L;
	private String message;

	public IllegalValetTokenException(String message){
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
