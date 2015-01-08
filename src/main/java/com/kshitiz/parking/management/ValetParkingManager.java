package com.kshitiz.parking.management;

import com.kshitiz.parking.entities.ValetToken;
import com.kshitiz.parking.entities.Vehicle;
import com.kshitiz.parking.exception.IllegalValetTokenException;
import com.kshitiz.parking.exception.ParkingSpotUnavailableException;
import com.kshitiz.parking.exception.TheftDetectedException;
import com.kshitiz.parking.exception.ValetPersonUnavailableException;
import com.kshitiz.parking.exception.ValetTokenExhaustedException;

public interface ValetParkingManager {

	Vehicle getVehicle(ValetToken valetToken) throws TheftDetectedException, ValetPersonUnavailableException, IllegalValetTokenException;

	ValetToken park(Vehicle vehicle) throws ValetTokenExhaustedException, ValetPersonUnavailableException, ParkingSpotUnavailableException;

}
