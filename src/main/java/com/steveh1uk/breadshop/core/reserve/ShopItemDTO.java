package com.steveh1uk.breadshop.core.reserve;

import java.io.Serializable;
import java.math.BigDecimal;

public class ShopItemDTO implements Serializable	 {

	private static final long serialVersionUID = 6244360760458024485L;
	private Integer id;
	private String description;
	private Integer unitPrice; // pence
	private Integer numberAvailable;
	private int numberReserved;
	
	public ShopItemDTO() {}
	
	public ShopItemDTO(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getUnitPrice() {
		return unitPrice;
	}
	public BigDecimal getUnitPriceMain() {
		return MoneyUtil.convertToMain(unitPrice);
	}
	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Integer getNumberAvailable() {
		return numberAvailable;
	}
	public void setNumberAvailable(Integer numberAvailable) {
		this.numberAvailable = numberAvailable;
	}
	public int getNumberReserved() {
		return numberReserved;
	}
	public void setNumberReserved(int numberReserved) {
		this.numberReserved = numberReserved;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShopItemDTO other = (ShopItemDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "ShopItemDTO [id=" + id + ", description=" + description + ", unitPrice=" + unitPrice + ", numberAvailable="
				+ numberAvailable + ", numberReserved=" + numberReserved + "]";
	}
	
	

}
