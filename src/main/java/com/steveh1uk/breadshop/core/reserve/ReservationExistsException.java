package com.steveh1uk.breadshop.core.reserve;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReservationExistsException extends Exception {

	private static final long serialVersionUID = 5892191977400484730L;
	private static final Logger logger = LoggerFactory.getLogger(ReservationExistsException.class);


	public ReservationExistsException(String username) {
		logger.info("User " + username + " has already made a reservation today");
	}



}
