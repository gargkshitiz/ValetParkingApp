package com.kshitiz.parking.management.impl;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.kshitiz.parking.entities.ValetToken;
import com.kshitiz.parking.exception.ValetTokenExhaustedException;
import com.kshitiz.parking.management.impl.ValetTokenManagerImpl;
public class ValetTokenManagerImplTest {

	private ValetTokenManagerImpl valetTokenManager;
	private ValetToken valetToken;
	
	@BeforeMethod
	public void setup(){
		valetTokenManager = new ValetTokenManagerImpl(5);
	}
	
	@Test(expectedExceptions=ValetTokenExhaustedException.class)
	public void shouldThrowUnavailabilityException() throws ValetTokenExhaustedException{
		whenNoValetTokenIsFree().callGetValetToken();
	}

	@Test
	public void shouldReturnAnAvailableValetPerson() throws ValetTokenExhaustedException{
		callGetValetToken();
		Assert.assertNotNull(valetToken);
		Assert.assertTrue(valetToken.isAssigned());
	}
	
	private void callGetValetToken() throws ValetTokenExhaustedException {
		valetToken = valetTokenManager.getAvailableValetToken();
	}

	private ValetTokenManagerImplTest whenNoValetTokenIsFree() {
		for(ValetToken valetToken: valetTokenManager.getValetTokens()){
			valetToken.setAssigned(true);
		}
		return this;
	}

}
