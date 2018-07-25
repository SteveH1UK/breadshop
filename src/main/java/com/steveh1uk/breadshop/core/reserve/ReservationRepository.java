package com.steveh1uk.breadshop.core.reserve;

import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Integer>{

	 Reservation findByUsername(String username);
}
