package com.kshitiz.parking.entities;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class KeyTest {
	
	private Key key1;
	private Key key2;
	
	private static final int KEY_CHIP_NUMBER_1 = 1234;
	private static final String KEY_COLOR_1 = "Black";
	
	@BeforeMethod
	public void setup(){
		key1 = new Key(KEY_CHIP_NUMBER_1, KEY_COLOR_1);
		key2 = new Key(KEY_CHIP_NUMBER_1, KEY_COLOR_1);
	}
	
	@Test
	public void equals(){
		Assert.assertEquals(key1, key2);
	}
	
	@Test
	public void unEqual(){
		key2 = new Key(0, KEY_COLOR_1);
		Assert.assertNotEquals(key1, key2);
	}
}
