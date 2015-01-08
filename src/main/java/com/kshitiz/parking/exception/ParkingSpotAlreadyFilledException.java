package com.kshitiz.parking.exception;

public class ParkingSpotAlreadyFilledException extends Exception {

	private static final long serialVersionUID = -1307511355634216155L;
	private String message;

	public ParkingSpotAlreadyFilledException(String message){
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
