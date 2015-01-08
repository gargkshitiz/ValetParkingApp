package com.kshitiz.parking.entities;

import java.util.ArrayList;
import java.util.List;

import com.kshitiz.parking.exception.ParkingSpotUnavailableException;

public class ParkingLot {
	
	private List<ParkingSpot> parkingSpots;
	
	public ParkingLot(int smallSizedParkingSpots,int midSizedParkingSpots,int largeSizedParkingSpots){
		this.parkingSpots = new ArrayList<ParkingSpot>(smallSizedParkingSpots+midSizedParkingSpots+largeSizedParkingSpots);
		for(int i =0; i< smallSizedParkingSpots; i++){
			parkingSpots.add(new ParkingSpot(Size.SMALL, "BS"+i));
		}
		for(int i =0; i< midSizedParkingSpots; i++){
			parkingSpots.add(new ParkingSpot(Size.MEDIUM, "BM"+i));
		}
		for(int i =0; i< largeSizedParkingSpots; i++){
			parkingSpots.add(new ParkingSpot(Size.LARGE, "BL"+i));
		}
	}

	public ParkingSpot getParkingSpotFor(Vehicle vehicle) throws ParkingSpotUnavailableException {
		for(ParkingSpot parkingSpot: parkingSpots){
			if(parkingSpot.isFree() && vehicle.canFitAt(parkingSpot)){
				return parkingSpot;
			}
		}
		throw new ParkingSpotUnavailableException("Oops, vehicle can't be parked in this parking lot...");
	}
	
	List<ParkingSpot> getParkingSpots() {
		return parkingSpots;
	}
}

