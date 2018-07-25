package com.steveh1uk.breadshop.core.reserve;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ShopItemsReaderSpringJdbc {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<ShopItemDTO> findShopItems() {
		
		String sql = "SELECT r.SHOP_ITEM_ID, r.DESCRIPTION, r.UNIT_PRICE, r.NUMBER_AVAILABLE,  NVL((SELECT q.QUANTITY_RESERVED FROM shop_items_reserved q WHERE q.shop_item_id = r.shop_item_id),0) AS QUANTITY_RESERVED" + 
				" from shop_items_to_reserve_vw r";

		
		return jdbcTemplate.query(sql, new Object[] {}, (rs,  rowNumber) -> {
			ShopItemDTO shopItemDTO = new ShopItemDTO();
			shopItemDTO.setId(rs.getInt("SHOP_ITEM_ID"));  
			shopItemDTO.setDescription(rs.getString("DESCRIPTION")); 
			shopItemDTO.setUnitPrice(rs.getInt("UNIT_PRICE"));
			shopItemDTO.setNumberAvailable( (rs.getInt("NUMBER_AVAILABLE")) - (rs.getInt("QUANTITY_RESERVED")));
			shopItemDTO.setNumberReserved(0);
            return shopItemDTO;
		});
	}
	
	public boolean reservationExistsForUser(String username) {
		String sql = "SELECT COUNT(*) FROM CURRENT_RESERVATIONS WHERE USERNAME = ?";
		int count = jdbcTemplate.queryForObject(
                sql, new Object[] { username }, Integer.class);
		return (count > 0);
	}

	/*
	 *  
 CREATE OR REPLACE VIEW reservation_vw
AS SELECT cure.reservation_id, sitr.shop_item_id, si.description, sitr.unit_price, sitr.number_available, rd.num_reserved
	 */
	public List<ShopItemDTO> findReservationShopItems(int reservationId) {
		
		String sql = "SELECT r.SHOP_ITEM_ID, r.DESCRIPTION, r.UNIT_PRICE, r.NUMBER_AVAILABLE,  r.NUM_RESERVED" + 
				" from reservation_vw r WHERE r.RESERVATION_ID = ?";

		
		return jdbcTemplate.query(sql, new Object[] {reservationId}, (rs,  rowNumber) -> {
			ShopItemDTO shopItemDTO = new ShopItemDTO();
			shopItemDTO.setId(rs.getInt("SHOP_ITEM_ID"));  
			shopItemDTO.setDescription(rs.getString("DESCRIPTION")); 
			shopItemDTO.setUnitPrice(rs.getInt("UNIT_PRICE"));
			shopItemDTO.setNumberAvailable( rs.getInt("NUMBER_AVAILABLE"));
			shopItemDTO.setNumberReserved(rs.getInt("NUM_RESERVED"));
            return shopItemDTO;
		});
	}
}
