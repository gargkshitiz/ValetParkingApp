package com.kshitiz.parking.management.impl;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.kshitiz.parking.entities.Key;
import com.kshitiz.parking.entities.ParkingLot;
import com.kshitiz.parking.entities.ParkingSpot;
import com.kshitiz.parking.entities.Size;
import com.kshitiz.parking.entities.ValetPerson;
import com.kshitiz.parking.entities.ValetToken;
import com.kshitiz.parking.entities.Vehicle;
import com.kshitiz.parking.exception.IllegalValetTokenException;
import com.kshitiz.parking.exception.IllegalVehicleKeyException;
import com.kshitiz.parking.exception.ParkingSpotUnavailableException;
import com.kshitiz.parking.exception.TheftDetectedException;
import com.kshitiz.parking.exception.ValetPersonUnavailableException;
import com.kshitiz.parking.exception.ValetTokenExhaustedException;
import com.kshitiz.parking.management.impl.ValetParkingManagerImpl;
import com.kshitiz.parking.management.impl.ValetPersonManagerImpl;
import com.kshitiz.parking.management.impl.ValetTokenManagerImpl;

public class ValetParkingManagerImplTest {
	
	@Mock private ParkingLot parkingLot;
	@Mock private ValetPersonManagerImpl valetPersonManager;
	@Mock private ValetTokenManagerImpl valetTokenManager;
	@Mock private ValetPerson valetPerson;
	private static final int KEY_CHIP_NUMBER_1 = 1234;
	private static final String KEY_COLOR_1 = "Black";
	private static final String REGISTRATION_NUMBER_1 = "DL8CR1234";
	private Vehicle vehicle;
	private ValetParkingManagerImpl valetParkingManagerImpl;
	private ValetToken valetToken;
	private Key key;
	
	@BeforeMethod
	public void setup(){
		MockitoAnnotations.initMocks(this);
		valetParkingManagerImpl = new ValetParkingManagerImpl(parkingLot, valetPersonManager, valetTokenManager);
		key = new Key(KEY_CHIP_NUMBER_1, KEY_COLOR_1);
		vehicle = new Vehicle(Size.MEDIUM, REGISTRATION_NUMBER_1, key);
		valetToken = new ValetToken(25);
	}
	
	@Test(expectedExceptions=ParkingSpotUnavailableException.class)
	public void parkWhenParkingSpotIsNotAvailable() throws ParkingSpotUnavailableException, ValetTokenExhaustedException, ValetPersonUnavailableException{
		whenParkingSpotIsNotAvailable().callPark();
	}

	@Test(expectedExceptions=ValetTokenExhaustedException.class)
	public void parkWhenValetTokenIsNotAvailable() throws ParkingSpotUnavailableException, ValetTokenExhaustedException, ValetPersonUnavailableException{
		whenValetTokenIsNotAvailable().callPark();
	}
	
	@Test(expectedExceptions=ValetPersonUnavailableException.class)
	public void parkWhenValetPersonIsNotAvailable() throws ParkingSpotUnavailableException, ValetTokenExhaustedException, ValetPersonUnavailableException{
		whenValetPersonIsNotAvailable().callPark();
	}
	
	@Test
	public void parkSuccessfullyWhenRealObjectsAreUsed() throws ValetTokenExhaustedException, ValetPersonUnavailableException, ParkingSpotUnavailableException{
		whenRealObjectsAreUsed().callPark();
	}
	
	@Test(expectedExceptions=ValetPersonUnavailableException.class)
	public void getVehicleWhenValetPersonIsNotAvailable() throws TheftDetectedException, ValetPersonUnavailableException, IllegalValetTokenException{
		whenValetPersonIsNotAvailable().andValetTokenIsValid().callGetVehicle();
	}

	@Test(expectedExceptions=IllegalValetTokenException.class)
	public void getVehicleWhenValetTokenIsIncorrect() throws TheftDetectedException, ValetPersonUnavailableException, IllegalValetTokenException{
		whenVehicleWhenValetTokenIsIncorrect().callGetVehicle();
	}
	
	@Test(expectedExceptions=TheftDetectedException.class)
	public void getVehicleWhenValetPersonReportsTheft() throws TheftDetectedException, ValetPersonUnavailableException, IllegalVehicleKeyException, IllegalValetTokenException, ValetTokenExhaustedException, ParkingSpotUnavailableException{
		whenValetPersonReportsTheft().andValetTokenIsValid().callGetVehicle(valetToken);
	}
	
	private ValetParkingManagerImplTest andValetTokenIsValid() {
		valetParkingManagerImpl.getValetTokenAndVehicleKeyMapping().put(valetToken, key);
		return this;
	}

	@Test
	public void getVehicleSomeTimeAfterParkingWhenRealObjectsAreUsed() throws TheftDetectedException, ValetPersonUnavailableException, IllegalVehicleKeyException, ValetTokenExhaustedException, ParkingSpotUnavailableException, InterruptedException, IllegalValetTokenException{
		whenRealObjectsAreUsed().callPark();
		waitForTenSecs();
		Vehicle receivedVehicle = callGetVehicle(valetToken);
		Assert.assertEquals(receivedVehicle, vehicle);
	}
	
	private ValetParkingManagerImplTest whenVehicleWhenValetTokenIsIncorrect() {
		return this;
	}
	
	private Vehicle callGetVehicle(ValetToken valetToken) throws TheftDetectedException, ValetPersonUnavailableException, IllegalValetTokenException {
		return valetParkingManagerImpl.getVehicle(valetToken);
	}

	private ValetParkingManagerImplTest whenValetPersonReportsIllegalKey() throws ValetPersonUnavailableException, TheftDetectedException, IllegalVehicleKeyException {
		Mockito.when(valetPersonManager.getAvailableValetPerson()).thenReturn(valetPerson);
		Mockito.when(valetPerson.getVehicle(Mockito.any(Key.class), Mockito.any(ParkingSpot.class))).thenThrow(IllegalVehicleKeyException.class);
		return this;
	}

	private ValetParkingManagerImplTest whenValetPersonReportsTheft() throws ValetPersonUnavailableException, TheftDetectedException, IllegalVehicleKeyException {
		Mockito.when(valetPersonManager.getAvailableValetPerson()).thenReturn(valetPerson);
		Mockito.when(valetPerson.getVehicle(Mockito.any(Key.class), Mockito.any(ParkingSpot.class))).thenThrow(TheftDetectedException.class);
		return this;
	}

	private void callGetVehicle() throws TheftDetectedException, ValetPersonUnavailableException, IllegalValetTokenException {
		vehicle = valetParkingManagerImpl.getVehicle(valetToken);
	}

	private ValetParkingManagerImplTest whenRealObjectsAreUsed() {
		parkingLot = new ParkingLot(10, 20, 20);
		valetPersonManager = new ValetPersonManagerImpl(10);
		valetTokenManager = new ValetTokenManagerImpl(50);
		valetParkingManagerImpl = new ValetParkingManagerImpl(parkingLot, valetPersonManager, valetTokenManager);
		return this;
	}
	
	private ValetParkingManagerImplTest whenValetPersonIsNotAvailable() throws ValetPersonUnavailableException {
		Mockito.when(valetPersonManager.getAvailableValetPerson()).thenThrow(ValetPersonUnavailableException.class);
		return this;
	}

	private ValetParkingManagerImplTest whenValetTokenIsNotAvailable() throws ValetTokenExhaustedException {
		Mockito.when(valetTokenManager.getAvailableValetToken()).thenThrow(ValetTokenExhaustedException.class);
		return this;
	}

	private void callPark() throws ValetTokenExhaustedException, ValetPersonUnavailableException, ParkingSpotUnavailableException {
		valetToken = valetParkingManagerImpl.park(vehicle);
	}

	private ValetParkingManagerImplTest waitForTenSecs() throws InterruptedException {
		Thread.sleep(5000);
		return this;
	}
	
	private ValetParkingManagerImplTest whenParkingSpotIsNotAvailable() throws ParkingSpotUnavailableException {
		Mockito.when(parkingLot.getParkingSpotFor(Mockito.any(Vehicle.class))).thenThrow(ParkingSpotUnavailableException.class);
		return this;
	}
}
