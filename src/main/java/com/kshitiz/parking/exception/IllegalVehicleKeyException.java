package com.kshitiz.parking.exception;

public class IllegalVehicleKeyException extends Exception {

	private static final long serialVersionUID = 7902233669259943397L;
	private String message;

	public IllegalVehicleKeyException(String message){
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
