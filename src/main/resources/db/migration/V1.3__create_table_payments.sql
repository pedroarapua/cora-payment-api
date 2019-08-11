CREATE TABLE payments (
  id char(36) NOT NULL,
  amount decimal(15,2) NOT NULL,
  type varchar(50) NOT NULL,
  status varchar(50) NULL,
  barcode_number varchar(44) NULL,
  buyer_id char(36) NOT NULL,
  client_id char(36) NOT NULL,
  credit_card_id char(36) NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (buyer_id) REFERENCES buyers(id),
  FOREIGN KEY (client_id) REFERENCES clients(id),
  FOREIGN KEY (credit_card_id) REFERENCES credit_cards(id)
)
