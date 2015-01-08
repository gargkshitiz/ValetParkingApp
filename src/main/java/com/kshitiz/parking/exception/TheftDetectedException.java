package com.kshitiz.parking.exception;

public class TheftDetectedException extends Exception {
	
	private static final long serialVersionUID = 7073353073700740939L;
	private String message;

	public TheftDetectedException(String message){
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
