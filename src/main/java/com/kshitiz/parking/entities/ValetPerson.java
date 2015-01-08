package com.kshitiz.parking.entities;

import com.kshitiz.parking.exception.IllegalVehicleKeyException;
import com.kshitiz.parking.exception.ParkingSpotAlreadyFilledException;
import com.kshitiz.parking.exception.TheftDetectedException;

public class ValetPerson {
	
	private boolean isWorking;
	
	public boolean isWorking() {
		return isWorking;
	}

	public void setWorking(boolean isWorking) {
		this.isWorking = isWorking;
	}

	public void park(Vehicle vehicle, ParkingSpot parkingSpot) throws ParkingSpotAlreadyFilledException {
		parkingSpot.park(vehicle);
	}

	public Vehicle getVehicle(Key key, ParkingSpot parkingSpot) throws TheftDetectedException, IllegalVehicleKeyException {
		return parkingSpot.getVehicle(key);
	}
}
