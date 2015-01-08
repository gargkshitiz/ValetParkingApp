package com.kshitiz.parking.exception;

public class ValetTokenExhaustedException extends Exception {

	private static final long serialVersionUID = -8296789968198573151L;
	private String message;

	public ValetTokenExhaustedException(String message){
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
}
