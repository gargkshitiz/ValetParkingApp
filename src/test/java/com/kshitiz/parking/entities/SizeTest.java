package com.kshitiz.parking.entities;
import org.testng.Assert;
import org.testng.annotations.Test;
public class SizeTest {
	
	@Test
	public void checkAvailableSizesAreThree(){
		Size[] sizes = Size.values();
		Assert.assertEquals(sizes.length, 3);
	}
	
	@Test
	public void checkAssignedRanksAreCorrect() throws Exception{
		Size[] sizes = Size.values();
		for(Size size: sizes){
			if(size.getRank()<1 || size.getRank()>3){
				throw new Exception();
			}
		}
		Assert.assertEquals(Size.SMALL.getRank(), 1);
		Assert.assertEquals(Size.MEDIUM.getRank(), 2);
		Assert.assertEquals(Size.LARGE.getRank(), 3);
	}
	
}
