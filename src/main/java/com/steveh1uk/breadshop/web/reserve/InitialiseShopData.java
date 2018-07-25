package com.steveh1uk.breadshop.web.reserve;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.steveh1uk.breadshop.core.reserve.BreadReservationServiceFacade;
import com.steveh1uk.breadshop.core.reserve.ReservationDTO;
import com.steveh1uk.breadshop.core.reserve.exception.NoReservationFound;
import com.steveh1uk.breadshop.web.utils.WebUtils;

public class InitialiseShopData {
	
	private static final Logger logger = LoggerFactory.getLogger(InitialiseShopData.class);

	private final BreadReservationServiceFacade breadReservationServiceFacade;

	public InitialiseShopData(BreadReservationServiceFacade breadReservationServiceFacade) {
		this.breadReservationServiceFacade = breadReservationServiceFacade;
	}

	public void initialise(ReservationDTO reservationDTO) {
		String username = WebUtils.getUserId();
		try {
			 breadReservationServiceFacade.populateUsersReservation(username, reservationDTO);
		} catch (NoReservationFound e) {

			HttpServletRequest request = (HttpServletRequest) (FacesContext.getCurrentInstance().getExternalContext()
					.getRequest());
			HttpServletResponse response = (HttpServletResponse) (FacesContext.getCurrentInstance().getExternalContext()
					.getResponse());
			try {
				response.sendRedirect(request.getContextPath() + "/reserve");
			} catch (IOException e2) {
				logger.warn("fail to redirect to reserve page " + e2.getMessage());
			}
		}
		logger.info("Initialising Username: " + username);
	}
}