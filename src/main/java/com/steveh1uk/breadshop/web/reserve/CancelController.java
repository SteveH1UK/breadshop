package com.steveh1uk.breadshop.web.reserve;

import javax.annotation.PostConstruct;

import com.steveh1uk.breadshop.core.reserve.BreadReservationServiceFacade;
import com.steveh1uk.breadshop.core.reserve.ReservationDTO;

/**
 * Display confirmation of a cancelled order
 * 
 * @author steve
 *
 */
public class CancelController {

	private BreadReservationServiceFacade breadReservationServiceFacade;
	private ReservationDTO reservationDTO;

	@PostConstruct
	public void initialiseShopItem() {
		new InitialiseShopData(breadReservationServiceFacade).initialise(reservationDTO);
	}

	/***
	 * Accessor methods
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
