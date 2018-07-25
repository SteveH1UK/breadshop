package com.steveh1uk.breadshop.core.reserve;

import com.steveh1uk.breadshop.core.reserve.exception.InvalidReservationRequestException;
import com.steveh1uk.breadshop.core.reserve.exception.InvalidReservationStatusException;
import com.steveh1uk.breadshop.core.reserve.exception.NoReservationFound;

/**
 * Bread Reservation Service Facade in core for other layers
 *
 */
public interface BreadReservationServiceFacade {
	
    /**
     * Initialise the reservation page with data from the database
     * @param reservationDTO - request with zero user counts
     * @param username - user id to reserve bread for
     * @throws ReservationExistsException - you can only make one request per day
     */
	void initialiseReservation(ReservationDTO reservationDTO, String username) throws ReservationExistsException;
	
	/**
	 *  Updates DTO with the Reservation id
	 *  @param reservationDTO - reservation to be made
	 *  @param username - user id to reserve bread for
	 *  @throws InvalidReservationRequestException - error in the request
	 *  @throws ReservationExistsException - you can only make one request per day
	 */
	void reserveBread(ReservationDTO reservationDTO, String username) throws InvalidReservationRequestException, ReservationExistsException;
		
	/**
	 * Cancel a reservation
	 * @param username - user id to cancel the reservation for
	 * @throws NoReservationFound - no reservation to cancel
	 * @throws InvalidReservationStatusException - invalid status to cancel (already cancelled or collected)
	 */
	void cancelReservation(String username) throws NoReservationFound, InvalidReservationStatusException;

	/**
	 * Populate user's current reservation
	 * @param username - for user id
	 * @param reservationDTO - reservation to populate
	 * @throws NoReservationFound - no reservation yet made
	 */
	void populateUsersReservation(String username, ReservationDTO reservationDTO) throws NoReservationFound;
}
