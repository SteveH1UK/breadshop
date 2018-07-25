package com.steveh1uk.breadshop.core.reserve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.steveh1uk.breadshop.core.reserve.exception.InvalidReservationStatusException;
import com.steveh1uk.breadshop.core.reserve.exception.NoReservationFound;

@Service
public class CancelReservationService {
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Transactional
	public void cancelReservation(String username) throws NoReservationFound, InvalidReservationStatusException {
		
		Reservation reservation = reservationRepository.findByUsername(username);
        if (reservation == null) {
        	throw new NoReservationFound();
        }
        
        if (reservation.isCancelled() || reservation.isCollected()) {
			throw new InvalidReservationStatusException("Request cancelation, cancelled = " + reservation.isCancelled() + "  collected = " + reservation.isCollected()); 
		}
        
        reservation.setCancelled(true);
        reservationRepository.save(reservation);
	}

}
