package com.steveh1uk.breadshop.core.reserve;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitialiseReservationService {

	@Autowired
	private ShopItemsReaderSpringJdbc shopItemsReader;

	public List<ShopItemDTO>  initialiseReservation(String username) throws ReservationExistsException {
		
		if (shopItemsReader.reservationExistsForUser(username)) {
			throw new ReservationExistsException(username);
		}
		
		return shopItemsReader.findShopItems();
	}

}
