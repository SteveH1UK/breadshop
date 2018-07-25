package com.steveh1uk.breadshop.core.reserve;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import com.steveh1uk.breadshop.core.config.BreadshopSpringConfig;
import com.steveh1uk.breadshop.core.reserve.exception.InvalidReservationRequestException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { BreadshopSpringConfig.class })
// Comment out below transactional annotation if I want to debug the test in the
// H2 Console
@Transactional
class MakeReservationServiceIntegrationTest {

	@Autowired
	private MakeReservationService makeReservationService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	void createNewReservation() throws InvalidReservationRequestException, ReservationExistsException {

		ReservationDTO newReservationDTO = new ReservationDTO();

		List<ShopItemDTO> shopItemDTOs = new ArrayList<>();
		newReservationDTO.setShopItems(shopItemDTOs);

		ShopItemDTO shopItem1 = new ShopItemDTO();
		shopItem1.setId(100);
		shopItem1.setNumberReserved(0);
		shopItemDTOs.add(shopItem1);

		ShopItemDTO shopItem2 = new ShopItemDTO();
		shopItem2.setId(101);
		shopItem2.setNumberReserved(02);
		shopItemDTOs.add(shopItem2);

		ShopItemDTO shopItem3 = new ShopItemDTO();
		shopItem3.setId(102);
		shopItem3.setNumberReserved(3);
		shopItemDTOs.add(shopItem3);

		Reservation newReservation = makeReservationService.reserve(newReservationDTO, "john.doe@mail.com");

		entityManager.flush();

		System.out.println("New Reservation: " + newReservation);
		assertEquals(1, JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "CURRENT_RESERVATIONS",
				"USERNAME = 'john.doe@mail.com'"), "Number of database rows updated");
		assertNotNull(newReservation.getReservationId());
		assertEquals(3, newReservation.getReservationDetails().size());

	}

	@Test
	void reservationAlreadyMade() {

		ReservationDTO newReservationDTO = new ReservationDTO();

		List<ShopItemDTO> shopItemDTOs = new ArrayList<>();
		newReservationDTO.setShopItems(shopItemDTOs);

		ShopItemDTO shopItem1 = new ShopItemDTO();
		shopItem1.setId(100);
		shopItem1.setNumberReserved(1);
		shopItemDTOs.add(shopItem1);

		assertThrows(ReservationExistsException.class, () -> {
			makeReservationService.reserve(newReservationDTO, "jane.day@mail.com");
			entityManager.flush();
		});
	}

	@Test
	void requestMoreItemsThanPresent() {

		ReservationDTO newReservationDTO = new ReservationDTO();

		List<ShopItemDTO> shopItemDTOs = new ArrayList<>();
		newReservationDTO.setShopItems(shopItemDTOs);

		ShopItemDTO shopItem1 = new ShopItemDTO();
		shopItem1.setId(100);
		shopItem1.setNumberReserved(50);
		shopItemDTOs.add(shopItem1);

		ShopItemDTO shopItem2 = new ShopItemDTO();
		shopItem2.setId(101);
		shopItem2.setNumberReserved(41);
		shopItemDTOs.add(shopItem2);

		ShopItemDTO shopItem3 = new ShopItemDTO();
		shopItem3.setId(102);
		shopItem3.setNumberReserved(34); // same as is left
		shopItemDTOs.add(shopItem3);

		Throwable exception = assertThrows(InvalidReservationRequestException.class, () -> {

			makeReservationService.reserve(newReservationDTO, "john.doe@mail.com");
			entityManager.flush();
		});
	    assertEquals("Invalid quantity requested [You can not order more items that are currently in reserve for Sour Dought Brown Bread, You can not order more items that are currently in reserve for French Bread]", exception.getMessage());

	}

}
