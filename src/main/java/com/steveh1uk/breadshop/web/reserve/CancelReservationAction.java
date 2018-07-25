package com.steveh1uk.breadshop.web.reserve;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.steveh1uk.breadshop.core.reserve.BreadReservationServiceFacade;
import com.steveh1uk.breadshop.core.reserve.exception.InvalidReservationStatusException;
import com.steveh1uk.breadshop.core.reserve.exception.NoReservationFound;
import com.steveh1uk.breadshop.web.utils.WebUtils;

/*
 * Algorithm for cancelling a reservation and returning the next action
 */
public class CancelReservationAction {
	
	private static final Logger logger = LoggerFactory.getLogger(CancelReservationAction.class);

	private final BreadReservationServiceFacade breadReservationServiceFacade;
	
	public CancelReservationAction(BreadReservationServiceFacade breadReservationServiceFacade ) {
		this.breadReservationServiceFacade = breadReservationServiceFacade;
	}

	public String cancel() {
	logger.info("Cancelling your reservation" );
		
		try {
			breadReservationServiceFacade.cancelReservation(WebUtils.getUserId());
		} catch (NoReservationFound e) {
			logger.info(e.getMessage());
			return "pretty:reserve";
		} catch (InvalidReservationStatusException e) {
			logger.info(e.getMessage());
			return "pretty:alreadyReserved";
		}
	
		return "pretty:cancel";		
	}
}
