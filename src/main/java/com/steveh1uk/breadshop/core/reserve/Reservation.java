package com.steveh1uk.breadshop.core.reserve;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "current_reservations")
public class Reservation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="reservation_id")
	private Integer reservationId;

	private String username;

	private boolean collected;

	private boolean cancelled;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "reservation_id")
	private List<ReservationDetail> reservationDetails = new ArrayList<>();

	public Integer getReservationId() {
		return reservationId;
	}

	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isCollected() {
		return collected;
	}

	public void setCollected(boolean collected) {
		this.collected = collected;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	public List<ReservationDetail> getReservationDetails() {
		return reservationDetails;
	}

	public void setReservationDetails(List<ReservationDetail> reservationDetails) {
		this.reservationDetails = reservationDetails;
	}

	@Override
	public String toString() {
		return "Reservation [reservationId=" + reservationId + ", username=" + username + ", collected=" + collected
				+ ", cancelled=" + cancelled + ", reservationDetails=" + reservationDetails + "]";
	}
	
	

}
