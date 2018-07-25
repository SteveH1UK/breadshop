package com.steveh1uk.breadshop.core.reserve;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ReservationDTO {
	
	private static final String OPEN_STATUS = "OPEN";
	
	private Integer reservationId;
	
	private String status = OPEN_STATUS;
	
	private List<ShopItemDTO> shopItemDTOs = new ArrayList<>();

	public List<ShopItemDTO> getShopItems() {
		return shopItemDTOs;
	}

	public void setShopItems(List<ShopItemDTO> shopItemDTOs) {
		this.shopItemDTOs = shopItemDTOs;
	}
	
	public BigDecimal getSum() {
		Integer sum = new Integer(0);
		for (ShopItemDTO shopItemDTO : shopItemDTOs) {
			sum = sum + (shopItemDTO.getNumberReserved() * shopItemDTO.getUnitPrice());
		}
		return MoneyUtil.convertToMain(sum);
	}
	
	

	public Integer getReservationId() {
		return reservationId;
	}

	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(boolean cancelled, boolean collected) {
		if (cancelled) {
			status = "CANCELLED";
		}
		else if (collected) {
			status = "COLLECTED"; 
		}
		else {
			status = OPEN_STATUS;
		}
	}
	
	public boolean isOpenStatus() {
		return (OPEN_STATUS).equals(status);
	}

	@Override
	public String toString() {
		return "ReservationDTO [reservationId=" + reservationId + ", status=" + status + ", shopItemDTOs="
				+ shopItemDTOs + "]";
	}
	

}
