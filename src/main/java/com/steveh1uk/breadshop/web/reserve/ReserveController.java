package com.steveh1uk.breadshop.web.reserve;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.steveh1uk.breadshop.core.reserve.BreadReservationServiceFacade;
import com.steveh1uk.breadshop.core.reserve.ReservationDTO;
import com.steveh1uk.breadshop.core.reserve.ReservationExistsException;
import com.steveh1uk.breadshop.core.reserve.exception.InvalidReservationRequestException;
import com.steveh1uk.breadshop.web.utils.WebContstants;
import com.steveh1uk.breadshop.web.utils.WebUtils;


public class ReserveController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReserveController.class);

	private ReservationDTO reservationDTO;

	private BreadReservationServiceFacade breadReservationServiceFacade;

	@PostConstruct
	public void initialiseShopItem() {
		String username = WebUtils.getUserId();
		try {
			breadReservationServiceFacade.initialiseReservation(reservationDTO, username);
		} catch (ReservationExistsException e) {

			HttpServletRequest request = (HttpServletRequest) (FacesContext.getCurrentInstance().getExternalContext()
					.getRequest());
			HttpServletResponse response = (HttpServletResponse) (FacesContext.getCurrentInstance().getExternalContext()
					.getResponse());
			try {
				response.sendRedirect(request.getContextPath() + "/alreadyReserved");
			} catch (IOException e2) {
				logger.info("fail to redirect to already reserved page " + e2.getMessage());
			}
		}

		logger.info("Initialising Username: " + username);
	}

	/**
	 * Action to make a bread reservation
	 */
	public String reserveBread() {

		try {
			breadReservationServiceFacade.reserveBread(reservationDTO, WebUtils.getUserId());
		} catch (InvalidReservationRequestException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
					e.getMessage(), ""));
					return WebContstants.FAILURE_RESPONSE;

		} catch (ReservationExistsException e) {
			HttpServletRequest request = (HttpServletRequest) (FacesContext.getCurrentInstance().getExternalContext()
					.getRequest());
			HttpServletResponse response = (HttpServletResponse) (FacesContext.getCurrentInstance().getExternalContext()
					.getResponse());
			try {
				response.sendRedirect(request.getContextPath() + "/alreadyReserved");
			} catch (IOException e2) {
				logger.info("fail to redirect to already reserved page" + e2.getMessage());
			}
		}
		logger.info("Reserving " + reservationDTO);
		return WebContstants.SUCCESS_RESPONSE;
	}

	public ReservationDTO getReservation() {
		return reservationDTO;
	}

	public void setReservation(ReservationDTO reservationDTO) {
		this.reservationDTO = reservationDTO;
	}

	public BreadReservationServiceFacade getBreadReservationServiceFacade() {
		return breadReservationServiceFacade;
	}

	public void setBreadReservationServiceFacade(BreadReservationServiceFacade breadReservationServiceFacade) {
		this.breadReservationServiceFacade = breadReservationServiceFacade;
	}

}
