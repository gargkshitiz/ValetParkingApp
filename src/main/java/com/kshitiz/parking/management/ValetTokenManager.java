package com.kshitiz.parking.management;

import com.kshitiz.parking.entities.ValetToken;
import com.kshitiz.parking.exception.ValetTokenExhaustedException;

public interface ValetTokenManager {

	ValetToken getAvailableValetToken() throws ValetTokenExhaustedException;

}
