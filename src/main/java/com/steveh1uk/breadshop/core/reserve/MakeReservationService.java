package com.steveh1uk.breadshop.core.reserve;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.steveh1uk.breadshop.core.reserve.exception.ApplicationUnexpectedError;
import com.steveh1uk.breadshop.core.reserve.exception.InvalidReservationRequestException;
import com.steveh1uk.breadshop.core.reserve.exception.NoReservationFound;

@Service
public class MakeReservationService {

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	private ShopItemsReaderSpringJdbc shopItemsReader;

	@Transactional(rollbackFor = InvalidReservationRequestException.class)
	public Reservation reserve(ReservationDTO reservationDTO, String username)
			throws InvalidReservationRequestException, ReservationExistsException {
		
		Reservation anyExistingReservation = reservationRepository.findByUsername(username);
		if (anyExistingReservation != null) {
			throw new ReservationExistsException(username);
		}

		Reservation newReservation = new Reservation();

		newReservation.setUsername(username);

		// Save the Reservation master record first (to get reservation id)
		reservationRepository.save(newReservation);

		List<ReservationDetail> details = new ArrayList<>();

		newReservation.setReservationDetails(details);

		// Needed to do this in two stages in order to get the reservation id for the
		// reservation detail
		for (ShopItemDTO shopItemDTO : reservationDTO.getShopItems()) {
			ReservationDetail detail = new ReservationDetail();
			detail.setId(new ReservationDetaiId(shopItemDTO.getId(), newReservation.getReservationId()));
			detail.setQuantity(shopItemDTO.getNumberReserved());
			details.add(detail);
		}

		validateRequest(newReservation);

		// save reservation details within the reservation now we have the reservation id
		reservationRepository.save(newReservation);

		reservationDTO.setReservationId(newReservation.getReservationId());
		reservationDTO.setStatus(false, false);

		return newReservation;

	}

	private void validateRequest(Reservation newReservation) throws InvalidReservationRequestException {
		int sum = 0;
		for (ReservationDetail item : newReservation.getReservationDetails()) {
			if (item.getQuantity() < 0) {
				throw new InvalidReservationRequestException("Quantity must be a positive value");
			}
			sum = sum + item.getQuantity();
		}
		if (sum == 0) {
			throw new InvalidReservationRequestException("You must enter at least one item");
		}
		List<String> itemErrors = new ArrayList<>();
		List<ShopItemDTO> allShopItems = shopItemsReader.findShopItems();
		for (ReservationDetail detail : newReservation.getReservationDetails()) {
			ShopItemDTO masterShopItemDTO = allShopItems.stream()
					.filter(detailItem -> detail.getId().getShopItemId().equals(detailItem.getId()))
					.findAny()
					.orElseThrow( () -> new ApplicationUnexpectedError("Can not find shop item with id " + detail.getId().getShopItemId() + " on list of shop items returned from the database"));
			if (detail.getQuantity() > masterShopItemDTO.getNumberAvailable()) {
				itemErrors.add("You can not order more items that are currently in reserve for " + masterShopItemDTO.getDescription());
			}
		}
		if ( ! itemErrors.isEmpty()) {
			throw new InvalidReservationRequestException("Invalid quantity requested " + itemErrors.toString());
		}
	}

	@Transactional(readOnly = true)
	public void  populateUsersReservation(String username, ReservationDTO reservationDTO) throws NoReservationFound {
		Reservation reservation = reservationRepository.findByUsername(username);
		if (reservation != null) {
			reservationDTO.setReservationId(reservation.getReservationId());
			reservationDTO.setStatus(reservation.isCancelled(), reservation.isCollected());
			reservationDTO.setShopItems(shopItemsReader.findReservationShopItems(reservation.getReservationId()));
		}
		else {
			throw new NoReservationFound();
		}
	}

}
