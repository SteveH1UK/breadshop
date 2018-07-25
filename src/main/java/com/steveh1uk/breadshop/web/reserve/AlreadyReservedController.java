package com.steveh1uk.breadshop.web.reserve;

import javax.annotation.PostConstruct;

import com.steveh1uk.breadshop.core.reserve.BreadReservationServiceFacade;
import com.steveh1uk.breadshop.core.reserve.ReservationDTO;

public class AlreadyReservedController {
	
	private BreadReservationServiceFacade breadReservationServiceFacade;

	private ReservationDTO reservationDTO;

	@PostConstruct
	public void initialiseShopItem() {
		new InitialiseShopData(breadReservationServiceFacade).initialise(reservationDTO);
	}
	
	/**
	 *  Action listener to cancel a bread reservation
	 */
	public String cancel() {
		 return new CancelReservationAction(breadReservationServiceFacade).cancel();
	}

	/***
	 *   Accessor methods
	 */


	public ReservationDTO getReservation() {
		return reservationDTO;
	}


	public void setReservation(ReservationDTO reservationDTO) {
		this.reservationDTO = reservationDTO;
	}

	public void setBreadReservationServiceFacade(BreadReservationServiceFacade breadReservationServiceFacade) {
		this.breadReservationServiceFacade = breadReservationServiceFacade;
	}
	
	
	

}
