package com.kshitiz.parking.management;

import com.kshitiz.parking.entities.ValetPerson;
import com.kshitiz.parking.exception.ValetPersonUnavailableException;

public interface ValetPersonManager {

	ValetPerson getAvailableValetPerson() throws ValetPersonUnavailableException;

}
