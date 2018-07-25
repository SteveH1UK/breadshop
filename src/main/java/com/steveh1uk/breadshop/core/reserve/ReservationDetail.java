package com.steveh1uk.breadshop.core.reserve;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="reservation_details")
public class ReservationDetail implements Serializable {

	private static final long serialVersionUID = 8765931218777033803L;

	@EmbeddedId
	private ReservationDetaiId id;
	 
	private Integer quantity;



	public ReservationDetaiId getId() {
		return id;
	}

	public void setId(ReservationDetaiId id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ReservationDetail [id=" + id + ", quantity=" + quantity + "]";
	}
	 
	 

}
