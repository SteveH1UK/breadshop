CREATE TABLE shop_items (
  shop_item_id         INTEGER PRIMARY KEY,
  description          VARCHAR(250) NOT NULL
);

-- Add reservation_date if we want to allow user to setup future dates (past data goes into MIS tables)
CREATE TABLE shop_items_to_reserve (
  shop_item_id         INTEGER PRIMARY KEY,
  unit_price           INTEGER NOT NULL,
  number_available     INTEGER NOT NULL,
  FOREIGN KEY (shop_item_id) REFERENCES shop_items(shop_item_id)
);

CREATE TABLE users (
  username             VARCHAR(100) PRIMARY KEY, 
  name                 VARCHAR(100) NOT NULL,
  password             VARCHAR(100) NOT NULL
);

CREATE TABLE current_reservations (
  reservation_id       INTEGER PRIMARY KEY,
  username             VARCHAR(100) NOT NULL,
  collected            BOOLEAN NOT NULL,
  cancelled            BOOLEAN NOT NULL,
  FOREIGN KEY (username) REFERENCES users(username)
);

CREATE UNIQUE INDEX uix_current_reservations ON current_reservations(username);

CREATE TABLE reservation_details (
  reservation_id       INTEGER NOT NULL,
  shop_item_id         INTEGER NOT NULL,
  quantity             INTEGER NOT NULL,
  CONSTRAINT pl_reservation_details PRIMARY KEY (reservation_id, shop_item_id),
  FOREIGN KEY (reservation_id) REFERENCES current_reservations(reservation_id),
  FOREIGN KEY (shop_item_id) REFERENCES shop_items_to_reserve(shop_item_id)
 );
 
 CREATE OR REPLACE VIEW reservation_vw
AS  SELECT cure.reservation_id, sitr.shop_item_id, si.description, sitr.unit_price, sitr.number_available, rd.quantity num_reserved
 FROM current_reservations cure
 INNER JOIN reservation_details rd ON cure.reservation_id = rd.reservation_id
 INNER JOIN shop_items si ON rd.shop_item_id = si.shop_item_id
 INNER JOIN shop_items_to_reserve sitr ON sitr.shop_item_id = si.shop_item_id;

 
CREATE OR REPLACE VIEW shop_items_to_reserve_vw
AS SELECT sitr.shop_item_id, si.description, sitr.unit_price, sitr.number_available
  FROM shop_items_to_reserve sitr
 INNER JOIN shop_items si ON sitr.shop_item_id = si.shop_item_id;
 
CREATE OR REPLACE VIEW shop_items_reserved
AS SELECT rd.shop_item_id, SUM(rd.quantity) quantity_reserved
FROM shop_items_to_reserve sitr
INNER JOIN reservation_details rd ON sitr.shop_item_id = rd.shop_item_id
INNER JOIN current_reservations cr ON cr.reservation_id = rd.reservation_id
WHERE cr.cancelled != 'true'
GROUP BY ( rd.shop_item_id);

CREATE SEQUENCE hibernate_sequence;