package com.kshitiz.parking.entities;

import com.kshitiz.parking.exception.IllegalVehicleKeyException;
import com.kshitiz.parking.exception.ParkingSpotAlreadyFilledException;
import com.kshitiz.parking.exception.TheftDetectedException;

public class ParkingSpot {

	private Size size;
	private String number;
	private Vehicle parkedVehicle;
	
	public ParkingSpot(Size size, String number) {
		this.size = size;
		this.number = number;
	}
	
	public boolean isFree() {
		return (parkedVehicle == null) ;
	}

	public void park(Vehicle vehicle) throws ParkingSpotAlreadyFilledException {
		if(this.parkedVehicle!=null){
			throw new ParkingSpotAlreadyFilledException("The parking spot is not free. Please give me the correct parking spot");
		}
		this.parkedVehicle = vehicle;
	}
	
	public Size getSize() {
		return size;
	}

	public Vehicle getVehicle(Key key) throws TheftDetectedException, IllegalVehicleKeyException{
		if(this.parkedVehicle == null){
			throw new TheftDetectedException("Warning. Vehicle might have got stolen from parkingspot: ".concat(String.valueOf(this.number))
							.concat(". Please contact vigilance department..."));
		}
		if(key.equals(parkedVehicle.getKey())){
			Vehicle vehicle = parkedVehicle;
			this.parkedVehicle = null;
			return vehicle;
		}
		throw new IllegalVehicleKeyException("Keys are not valid. Please take an appropriate action...");
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParkingSpot other = (ParkingSpot) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

	// For UTCs
	void stealVehicle() {
		this.parkedVehicle = null;
	}

}
