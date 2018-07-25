INSERT INTO users VALUES ('john.doe@mail.com', 'John Doe', 'password1');
INSERT INTO users VALUES ('fred.blogs@mail.com', 'Fred Bloggs', 'password2');
INSERT INTO users VALUES ('jane.day@mail.com', 'Jane Day', 'password3');

		
INSERT INTO shop_items VALUES (100, 'Sour Dought Brown Bread');
INSERT INTO shop_items VALUES (101, 'French Bread');
INSERT INTO shop_items VALUES (102, 'Schwartsbrot');

INSERT INTO shop_items_to_reserve VALUES (100, 325, 28);
INSERT INTO shop_items_to_reserve VALUES (101, 150, 40);
INSERT INTO shop_items_to_reserve VALUES (102, 220, 35);

INSERT INTO current_reservations VALUES (800, 'jane.day@mail.com', 'false', 'false');

INSERT INTO reservation_details VALUES (800, 100, 3);
INSERT INTO reservation_details VALUES (800, 102, 1);


