package com.steveh1uk.breadshop.core.reserve;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.steveh1uk.breadshop.core.reserve.exception.InvalidReservationRequestException;
import com.steveh1uk.breadshop.core.reserve.exception.InvalidReservationStatusException;
import com.steveh1uk.breadshop.core.reserve.exception.NoReservationFound;

@Service("breadReservationServiceFacade")
public class BreadReservationServiceFacadeImpl implements BreadReservationServiceFacade {
	
	private static final Logger logger = LoggerFactory.getLogger(BreadReservationServiceFacadeImpl.class);


	@Autowired
	private CancelReservationService cancelReservationService;
	
	@Autowired
	private InitialiseReservationService initialiseReservationService;
	
	@Autowired
	private MakeReservationService makeReservationService;

    @Override
	public void initialiseReservation(ReservationDTO reservationDTO, String username) throws ReservationExistsException {
		if (reservationDTO == null) {
			reservationDTO = new ReservationDTO();
		}
		
		List<ShopItemDTO> shopItemDTOs = initialiseReservationService.initialiseReservation(username);
		reservationDTO.setShopItems(shopItemDTOs);
			
		
		logger.info("making reservation " + reservationDTO);
	}

	@Override
	public void reserveBread(ReservationDTO reservationDTO, String username) throws InvalidReservationRequestException, ReservationExistsException {
		 makeReservationService.reserve(reservationDTO, username);
	}


	@Override
	public void populateUsersReservation(String username, ReservationDTO reservationDTO) throws NoReservationFound {
		 makeReservationService.populateUsersReservation(username, reservationDTO);
	}

	@Override
	public void cancelReservation(String username) throws NoReservationFound, InvalidReservationStatusException {
		cancelReservationService.cancelReservation(username);
	}
    
    

}
