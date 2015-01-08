package com.kshitiz.parking.entities;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ValetTokenTest {
	
	private static final int TOKEN_NUMBER_2 = 0;
	private ValetToken valetToken1;
	private ValetToken valetToken2;
	
	private static final int TOKEN_NUMBER_1 = 1234;
	
	@BeforeMethod
	public void setup(){
		valetToken1 = new ValetToken(TOKEN_NUMBER_1);
		valetToken2 = new ValetToken(TOKEN_NUMBER_1);
	}
	
	@Test
	public void equals(){
		Assert.assertEquals(valetToken1, valetToken2);
	}
	
	@Test
	public void unEqual(){
		valetToken2 = new ValetToken(TOKEN_NUMBER_2);
		Assert.assertNotEquals(valetToken1, valetToken2);
	}
}
