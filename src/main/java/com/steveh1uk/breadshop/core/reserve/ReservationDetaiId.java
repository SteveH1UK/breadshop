package com.steveh1uk.breadshop.core.reserve;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ReservationDetaiId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "shop_item_id")
	private Integer shopItemId;

	@Column(name="reservation_id")
	private Integer reservationId;

	public ReservationDetaiId() {
	}

	public ReservationDetaiId(Integer shopItemId, Integer reservationId) {
		super();
		this.shopItemId = shopItemId;
		this.reservationId = reservationId;
	}

	public Integer getShopItemId() {
		return shopItemId;
	}

	public void setShopItemId(Integer shopItemId) {
		this.shopItemId = shopItemId;
	}

	public Integer getReservationId() {
		return reservationId;
	}

	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}

	@Override
	public String toString() {
		return "ReservationDetaiId [shopItemId=" + shopItemId + ", reservationId=" + reservationId + "]";
	}
	
	


}
