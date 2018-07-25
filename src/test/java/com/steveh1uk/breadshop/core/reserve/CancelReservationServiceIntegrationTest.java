package com.steveh1uk.breadshop.core.reserve;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import com.steveh1uk.breadshop.core.reserve.exception.InvalidReservationStatusException;
import com.steveh1uk.breadshop.core.reserve.exception.NoReservationFound;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BreadshopSpringConfig.class})
// Comment out below transactional annotation if I want to debug the test in the H2 Console
@Transactional
class CancelReservationServiceIntegrationTest {

	@Autowired
	CancelReservationService cancelReservationService;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@PersistenceContext
	private EntityManager entityManager;

    @Test
    void cancelReservation() throws NoReservationFound, InvalidReservationStatusException {
    	cancelReservationService.cancelReservation("jane.day@mail.com");
        entityManager.flush();

    	assertEquals( 1, JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "CURRENT_RESERVATIONS","USERNAME = 'jane.day@mail.com' AND collected='false' AND cancelled='true'"), "Number of database rows updated");    }
	
	@Test
	void noReservationMade() {
		assertThrows(NoReservationFound.class, () -> {
			cancelReservationService.cancelReservation("john.doe@mail.com");
	    });
	}
	
	

}
