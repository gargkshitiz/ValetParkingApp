package com.kshitiz.parking.exception;

public class ParkingSpotUnavailableException extends Exception {

	private static final long serialVersionUID = 3524266349509118139L;
	private String message;

	public ParkingSpotUnavailableException(String message){
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
