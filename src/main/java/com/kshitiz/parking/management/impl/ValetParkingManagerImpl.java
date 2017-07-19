package com.kshitiz.parking.management.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.kshitiz.parking.entities.Key;
import com.kshitiz.parking.entities.ParkingLot;
import com.kshitiz.parking.entities.ParkingSpot;
import com.kshitiz.parking.entities.ValetPerson;
import com.kshitiz.parking.entities.ValetToken;
import com.kshitiz.parking.entities.Vehicle;
import com.kshitiz.parking.exception.IllegalValetTokenException;
import com.kshitiz.parking.exception.ValetPersonUnavailableException;
import com.kshitiz.parking.exception.ValetTokenExhaustedException;
import com.kshitiz.parking.exception.IllegalVehicleKeyException;
import com.kshitiz.parking.exception.ParkingSpotUnavailableException;
import com.kshitiz.parking.exception.TheftDetectedException;
import com.kshitiz.parking.management.ValetParkingManager;
import com.kshitiz.parking.task.ParkTheCarTask;

public class ValetParkingManagerImpl implements ValetParkingManager {
	
	private Map<ValetToken, Key> valetTokenAndVehicleKeyMapping;
	private Map<ValetToken, ParkingSpot> valetTokenAndParkingSpotMapping;
	private ParkingLot parkingLot;
	private ValetPersonManagerImpl valetPersonManager;
	private ValetTokenManagerImpl valetTokenManager;
	
	public ValetParkingManagerImpl(ParkingLot parkingLot, ValetPersonManagerImpl valetPersonManager, ValetTokenManagerImpl valetTokenManager){
		this.parkingLot = parkingLot;
		this.valetPersonManager = valetPersonManager;
		this.valetTokenManager = valetTokenManager;
		this.valetTokenAndVehicleKeyMapping = new ConcurrentHashMap<ValetToken,Key>();
		this.valetTokenAndParkingSpotMapping =  new ConcurrentHashMap<ValetToken, ParkingSpot>();
	}
	
	@Override
	public ValetToken park(Vehicle vehicle) throws ValetTokenExhaustedException, 
										ValetPersonUnavailableException, ParkingSpotUnavailableException{
		ValetToken valetToken = valetTokenManager.getAvailableValetToken();
		ParkingSpot parkingSpot = parkingLot.getParkingSpotFor(vehicle);
		ValetPerson valetPerson = valetPersonManager.getAvailableValetPerson();
		valetTokenAndParkingSpotMapping.put(valetToken, parkingSpot);
		valetTokenAndVehicleKeyMapping.put(valetToken,vehicle.getKey());
		new Thread(new ParkTheCarTask(valetPerson, vehicle, parkingSpot)).start();
		return valetToken;
	}

	@Override
	public Vehicle getVehicle(ValetToken valetToken) throws TheftDetectedException, ValetPersonUnavailableException, IllegalValetTokenException {
		Key key = valetTokenAndVehicleKeyMapping.get(valetToken);
		if(key == null){
			throw new IllegalValetTokenException("Valet token is not correct. Please re-check...");
		}
		ParkingSpot parkingSpot = valetTokenAndParkingSpotMapping.get(valetToken);
		Vehicle vehicle = null ;
		try{
			ValetPerson valetPerson = valetPersonManager.getAvailableValetPerson() ;
			vehicle = valetPerson.getVehicle(key, parkingSpot);
		}
		catch(IllegalVehicleKeyException e){
			// TODO: Take appropriate action for incorrect mapping
			// Get the mapping correct and the give the correct key and parking spot
		}
		valetToken.setAssigned(false);
		return vehicle;
	}

	//For UTCs
	Map<ValetToken, Key> getValetTokenAndVehicleKeyMapping() {
		return valetTokenAndVehicleKeyMapping;
	}
	
}
