CREATE TABLE credit_cards (
  id char(36) NOT NULL,
  number varchar(100) NOT NULL,
  holder_name varchar(100) NOT NULL,
  month int(2) NOT NULL,
  year int(4) NOT NULL,
  issuer varchar(100) NOT NULL,
  buyer_id char(36) NOT NULL,
  PRIMARY KEY (id),
  KEY FK_CREDIT_CARD_BUYER (buyer_id)
)
