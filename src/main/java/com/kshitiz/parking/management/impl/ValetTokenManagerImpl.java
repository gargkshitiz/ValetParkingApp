package com.kshitiz.parking.management.impl;

import java.util.ArrayList;
import java.util.List;

import com.kshitiz.parking.entities.ValetToken;
import com.kshitiz.parking.exception.ValetTokenExhaustedException;
import com.kshitiz.parking.management.ValetTokenManager;

public class ValetTokenManagerImpl implements ValetTokenManager{
	private List<ValetToken> valetTokens;
	
	public ValetTokenManagerImpl(int totalValetTokens){
		this.valetTokens = new ArrayList<ValetToken>(totalValetTokens);
		for(int i = 0; i < totalValetTokens; i++){
			valetTokens.add(new ValetToken(i));
		}
	}

	@Override
	public ValetToken getAvailableValetToken() throws ValetTokenExhaustedException {
		for(ValetToken valetToken: valetTokens){
			if(!valetToken.isAssigned()){
				valetToken.setAssigned(true);
				return valetToken;
			}
		}
		throw new ValetTokenExhaustedException("Valet tickets are exhausted. Please wait for some time...");
	}

	List<ValetToken> getValetTokens() {
		return valetTokens;
	}
}
